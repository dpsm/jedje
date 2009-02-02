package br.org.cesar.jedje.javame.test;

import javax.microedition.lcdui.Canvas;

import br.org.cesar.jedje.JEdjeException;
import br.org.cesar.jedje.compiler.grammar.JEdjeCollection;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescription;
import br.org.cesar.jedje.compiler.grammar.JEdjeGroup;
import br.org.cesar.jedje.compiler.grammar.JEdjePart;
import br.org.cesar.jedje.javame.JEdjeCanvas;

public class JEdjeTestCanvas extends JEdjeCanvas {

	private int index = 0x03;
	
	public JEdjeTestCanvas(String _edje, String _group)
			throws JEdjeException {
		super(_edje, _group);
		
		JEdjeDescription description = null;
		JEdjeGroup		 group		 = null;
		JEdjePart 		 part	 	 = null;
		
		group = JEdjeCollection.getGroup(getDataCollection(), "menu");
		part  = JEdjeCollection.getPart(group, "icon3");
		if (part != null) {
			description = JEdjeCollection.getDescription(part, "selected");
			part.setCurrent(description);
		}
	}
	
	/* (non-Javadoc)
	 * @see br.org.cesar.jedje.javame.JEdjeCanvas#pointerReleasedEvent(br.org.cesar.jedje.compiler.grammar.JEdjePart, int, int)
	 */
	protected void pointerReleasedEvent(JEdjePart part, int x, int y) {
		String name  = part.getName();
		int oldIndex = this.index;
		this.index 	 = Integer.parseInt(String.valueOf(name.charAt(name.length() - 1)));
		event(oldIndex, index);
	}

	protected void keyReleased(int keycode) {
		int oldIndex = this.index;
		
		int action = getGameAction(keycode);
		switch (action) {
			case Canvas.LEFT:
				if (index > 1) {					
					index--;
				}
			break;
			case Canvas.RIGHT:
				if (index < 5) {					
					index++;
				}
			break;
		}
		
		event(oldIndex, index);
	}

	private void event(int oldIndex, int index2) {
		JEdjeDescription description = null;
		JEdjeGroup		 group		 = null;
		JEdjePart 		 part	 	 = null;
		
		if (oldIndex != this.index) {
			group = JEdjeCollection.getGroup(getDataCollection(), "menu");
			
			part  = JEdjeCollection.getPart(group, "icon" + oldIndex);
			if (part != null) {
				description = JEdjeCollection.getDescription(part, "default");
				part.setCurrent(description);
			}

			part  = JEdjeCollection.getPart(group, "icon3");
			if (part != null) {
				description = JEdjeCollection.getDescription(part, "pos" + index);
				part.setCurrent(description);
			}
			
			part  = JEdjeCollection.getPart(group, "icon" + index);
			if (part != null) {
				description = JEdjeCollection.getDescription(part, "selected");
				part.setCurrent(description);
			}
			
			part  = JEdjeCollection.getPart(group, "title");
			if (part != null) {
				description = JEdjeCollection.getDescription(part, "title" + index);
				part.setCurrent(description);
			}
			this.repaint();
		}
	}
	
}
