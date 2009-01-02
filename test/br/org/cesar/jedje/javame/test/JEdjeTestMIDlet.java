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

import br.org.cesar.jedje.compiler.grammar.JEdjeCollection;
import br.org.cesar.jedje.compiler.parser.JEdjeParser;
import br.org.cesar.jedje.compiler.parser.JEdjeScanner;
import br.org.cesar.jedje.javame.JEdjeCanvas;

public class JEdjeTestMIDlet extends MIDlet {

	private static JEdjeTestMIDlet instance;
	
	private JEdjeCollection collection; 
	private JEdjeCanvas   canvas;
	
	public JEdjeTestMIDlet() {
		InputStream  stream  = this.getClass().getResourceAsStream("/test.edc");
		try {
			JEdjeScanner  scanner  = new JEdjeScanner(stream);
			JEdjeParser   parser   = new JEdjeParser(scanner);
			this.collection = parser.parseDocument();
			this.canvas   = new JEdjeTestCanvas(collection, "menu");
			this.canvas.setFullScreenMode(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JEdjeTestMIDlet.instance = this;
	}

	public static JEdjeTestMIDlet getInstance() {
		return JEdjeTestMIDlet.instance;
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
