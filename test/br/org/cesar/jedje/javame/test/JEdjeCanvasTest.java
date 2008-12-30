package br.org.cesar.jedje.javame.test;

import javax.microedition.lcdui.Canvas;

import br.org.cesar.jedje.compiler.JEdjeException;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescription;
import br.org.cesar.jedje.compiler.grammar.JEdjeDocument;
import br.org.cesar.jedje.compiler.grammar.JEdjeGroup;
import br.org.cesar.jedje.compiler.grammar.JEdjePart;
import br.org.cesar.jedje.javame.JEdjeCanvas;

public class JEdjeCanvasTest extends JEdjeCanvas {

	private JEdjePart selected = null;
	
	public JEdjeCanvasTest(JEdjeDocument edje, String group)
			throws JEdjeException {
		super(edje, group);
	}
	
	protected void keyReleased(int keycode) {
		JEdjeDescription description = null;
		JEdjeGroup		 group		 = null;
		JEdjePart 		 part 		 = null;
		
		group = JEdjeDocument.getGroup(getDocument(), "menu");
		
		switch (keycode) {
			case Canvas.KEY_NUM1:
				part = JEdjeDocument.getPart(group, "icon1");
			break;
			case Canvas.KEY_NUM3:
				part = JEdjeDocument.getPart(group, "icon2");
			break;
			case Canvas.KEY_NUM7:
				part = JEdjeDocument.getPart(group, "icon3");
			break;
			case Canvas.KEY_NUM9:
				part = JEdjeDocument.getPart(group, "icon4");
			break;
			case Canvas.KEY_NUM0:
				JEdjeCanvasMIDlet.getInstance().notifyDestroyed();
			break;
		}
		
		
		if (part != null) {
			if (selected != null) {
				description = JEdjeDocument.getDescription(selected, "default");
				selected.setCurrent(description);
			}
			
			description = JEdjeDocument.getDescription(part, "selected");
			part.setCurrent(description);
			
			selected = part;
			this.repaint();
		}
	}
	
}
