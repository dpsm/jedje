package br.org.cesar.jedje.compiler.test;

import java.io.InputStream;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.org.cesar.jedje.compiler.grammar.JEdjeDocument;
import br.org.cesar.jedje.compiler.parser.JEdjeParser;
import br.org.cesar.jedje.compiler.parser.JEdjeScanner;
import br.org.cesar.jedje.javame.JEdjeCanvas;

public class JEdjeCanvasMIDlet extends MIDlet {

	private JEdjeCanvas canvas;

	public JEdjeCanvasMIDlet() {
		InputStream  stream  = this.getClass().getResourceAsStream("/test.edc");
		try {
			JEdjeScanner  scanner  = new JEdjeScanner(stream);
			JEdjeParser   parser   = new JEdjeParser(scanner);
			JEdjeDocument document = parser.parseDocument();
			this.canvas = new JEdjeCanvas(document, "test");
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

}
