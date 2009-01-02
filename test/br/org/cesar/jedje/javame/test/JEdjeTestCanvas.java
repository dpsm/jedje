package br.org.cesar.jedje.javame.test;

import javax.microedition.lcdui.Canvas;

import br.org.cesar.jedje.JEdjeException;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescription;
import br.org.cesar.jedje.compiler.grammar.JEdjeCollection;
import br.org.cesar.jedje.compiler.grammar.JEdjeGroup;
import br.org.cesar.jedje.compiler.grammar.JEdjePart;
import br.org.cesar.jedje.javame.JEdjeCanvas;

public class JEdjeTestCanvas extends JEdjeCanvas {

	private int index = 0x03;
	
	public JEdjeTestCanvas(JEdjeCollection _edje, String _group)
			throws JEdjeException {
		super(_edje, _group);
		
		JEdjeDescription description = null;
		JEdjeGroup		 group		 = null;
		JEdjePart 		 part	 	 = null;
		
		group = JEdjeCollection.getGroup(getDataCollection(), "menu");
		part  = JEdjeCollection.getPart(group, "icon" + index);
		if (part != null) {
			description = JEdjeCollection.getDescription(part, "selected");
			part.setCurrent(description);
		}
	}
	
	protected void keyReleased(int keycode) {
		JEdjeDescription description = null;
		JEdjeGroup		 group		 = null;
		JEdjePart 		 part	 	 = null;
		
		int oldIndex = this.index;
		
		int action = getGameAction(keycode);
		switch (action) {
			case Canvas.UP:
				if (index > 1) {					
					index--;
				}
			break;
			case Canvas.DOWN:
				if (index < 5) {					
					index++;
				}
			break;
		}
		
		if (oldIndex != this.index) {
			group = JEdjeCollection.getGroup(getDataCollection(), "menu");
			part  = JEdjeCollection.getPart(group, "icon" + oldIndex);
			if (part != null) {
				description = JEdjeCollection.getDescription(part, "default");
				part.setCurrent(description);
			}
			
			part  = JEdjeCollection.getPart(group, "icon" + index);
			if (part != null) {
				description = JEdjeCollection.getDescription(part, "selected");
				part.setCurrent(description);
			}
			this.repaint();
		}
	}
	
}
