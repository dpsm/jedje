/**
 * Copyright (c) 2008 David Marques.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     David Marques - Adding EPL headers.                     
 */
package br.org.cesar.jedje.compiler.test;

import java.io.InputStream;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.org.cesar.jedje.compiler.grammar.JEdjeColor;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescription;
import br.org.cesar.jedje.compiler.grammar.JEdjeDocument;
import br.org.cesar.jedje.compiler.grammar.JEdjePart;
import br.org.cesar.jedje.compiler.parser.JEdjeParser;
import br.org.cesar.jedje.compiler.parser.JEdjeScanner;
import br.org.cesar.jedje.javame.JEdjeCanvas;

public class JEdjeCanvasMIDlet extends MIDlet implements CommandListener {

	private JEdjeDocument document; 
	private JEdjeCanvas   canvas;
	private Command		  other;
	private Command	      exit;
	private boolean 	  flag;
	
	public JEdjeCanvasMIDlet() {
		InputStream  stream  = this.getClass().getResourceAsStream("/test.edc");
		try {
			JEdjeScanner  scanner  = new JEdjeScanner(stream);
			JEdjeParser   parser   = new JEdjeParser(scanner);
			this.document = parser.parseDocument();
			this.canvas   = new JEdjeCanvas(document, "test");
			this.other	  = new Command("Other", Command.OK, 1);
			this.exit 	  = new Command("Exit", Command.EXIT, 1);
			this.canvas.setCommandListener(this);
			this.canvas.addCommand(this.other);
			this.canvas.addCommand(this.exit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		if (this.canvas != null) {
			Display.getDisplay(this).setCurrent(canvas);
		}
	}

	public void commandAction(Command c, Displayable d) {
		if (c == this.exit) {
			this.notifyDestroyed();
		} else
		if (c == this.other) {
			showOther();
		}
	}

	private void showOther() {
		JEdjePart image1 = JEdjeDocument.getPart(this.document, "test", "image1");
		JEdjePart rect6  = JEdjeDocument.getPart(this.document, "test", "rect6");
		
		JEdjeDescription description = null;
		if (flag) {
			description = JEdjeDocument.getDescription(this.document, "test", "image1", "default");
		} else {
			description = JEdjeDocument.getDescription(this.document, "test", "image1", "other");
		}
		image1.setCurrent(description);
		
		description = null;
		if (flag) {
			description = JEdjeDocument.getDescription(this.document, "test", "rect6", "default");
		} else {
			description = JEdjeDocument.getDescription(this.document, "test", "rect6", "other");
		}
		rect6.setCurrent(description);
		flag = !flag;
		this.canvas.repaint();
	}

}
