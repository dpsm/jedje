package br.org.cesar.jedje.javame.test;

import javax.microedition.lcdui.Canvas;

import br.org.cesar.jedje.JEdjeException;
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
		JEdjePart 		 iconPart	 = null;
		JEdjePart 		 textPart	 = null;
		
		group = JEdjeDocument.getGroup(getDocument(), "menu");
		textPart = JEdjeDocument.getPart(group, "menu_text");
		
		switch (keycode) {
			case Canvas.KEY_NUM1:
				description = JEdjeDocument.getDescription(textPart, "option1");
				iconPart = JEdjeDocument.getPart(group, "icon1");
			break;
			case Canvas.KEY_NUM3:
				description = JEdjeDocument.getDescription(textPart, "option2");
				iconPart = JEdjeDocument.getPart(group, "icon2");
			break;
			case Canvas.KEY_NUM7:
				description = JEdjeDocument.getDescription(textPart, "option3");
				iconPart = JEdjeDocument.getPart(group, "icon3");
			break;
			case Canvas.KEY_NUM9:
				description = JEdjeDocument.getDescription(textPart, "option4");
				iconPart = JEdjeDocument.getPart(group, "icon4");
			break;
			case Canvas.KEY_NUM0:
				JEdjeCanvasMIDlet.getInstance().notifyDestroyed();
			break;
		}
		
		if (textPart != null) {
			textPart.setCurrent(description);
		}
		
		if (iconPart != null) {
			if (selected != null) {
				description = JEdjeDocument.getDescription(selected, "default");
				selected.setCurrent(description);
			}
			
			description = JEdjeDocument.getDescription(iconPart, "selected");
			iconPart.setCurrent(description);
			
			selected = iconPart;
		}
		this.repaint();
	}
	
}
