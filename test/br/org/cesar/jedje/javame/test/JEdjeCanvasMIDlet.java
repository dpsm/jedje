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
package br.org.cesar.jedje.javame.test;

import java.io.InputStream;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.org.cesar.jedje.compiler.grammar.JEdjeDocument;
import br.org.cesar.jedje.compiler.parser.JEdjeParser;
import br.org.cesar.jedje.compiler.parser.JEdjeScanner;
import br.org.cesar.jedje.javame.JEdjeCanvas;

public class JEdjeCanvasMIDlet extends MIDlet {

	private static JEdjeCanvasMIDlet instance;
	
	private JEdjeDocument document; 
	private JEdjeCanvas   canvas;
	
	public JEdjeCanvasMIDlet() {
		InputStream  stream  = this.getClass().getResourceAsStream("/test.edc");
		try {
			JEdjeScanner  scanner  = new JEdjeScanner(stream);
			JEdjeParser   parser   = new JEdjeParser(scanner);
			this.document = parser.parseDocument();
			this.canvas   = new JEdjeCanvasTest(document, "menu");
			this.canvas.setFullScreenMode(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JEdjeCanvasMIDlet.instance = this;
	}

	public static JEdjeCanvasMIDlet getInstance() {
		return JEdjeCanvasMIDlet.instance;
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

}
