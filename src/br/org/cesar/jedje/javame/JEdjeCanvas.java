package br.org.cesar.jedje.javame;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import br.org.cesar.jedje.compiler.JEdjeException;
import br.org.cesar.jedje.compiler.grammar.JEdjeColor;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescription;
import br.org.cesar.jedje.compiler.grammar.JEdjeDocument;
import br.org.cesar.jedje.compiler.grammar.JEdjeGroup;
import br.org.cesar.jedje.compiler.grammar.JEdjePart;
import br.org.cesar.jedje.compiler.grammar.JEdjeRel;
import br.org.cesar.jedje.compiler.grammar.JEdjeTuple;

public class JEdjeCanvas extends Canvas {
	
	private JEdjeDocument document;
	private JEdjeGroup 	  group;
	
	public JEdjeCanvas(JEdjeDocument edje, String group) throws JEdjeException {
		this.document = edje;
		this.loadEdjeGroup(group);
	}
	
	private void loadEdjeGroup(String name) throws JEdjeException {
		JEdjeGroup[] groups = this.document.getGroups();
		for (int i = 0; i < groups.length; i++) {
			JEdjeGroup group = groups[i];
			if (group.getName().equals(name)) {
				this.group = group;
				break;
			}
		}
		
		if (this.group == null) {
			throw new JEdjeException("Unable to load group " + name + ".");
		}
	}

	protected void paint(Graphics g) {
		JEdjePart[] parts = this.group.getParts();
		for (int i = 0; i < parts.length; i++) {
			this.drawPart(g, parts[i]);
		}
	}

	private void drawPart(Graphics g, JEdjePart edjePart) {
		JEdjeDescription current = edjePart.getCurrent();
		
		if (current != null) {
			JEdjeColor color = current.getColor();
			if (color != null) {
				g.setColor(color.getR(), color.getG(), color.getB());
			}
			
			switch (edjePart.getType()) {
				case JEdjePart.RECT:
					drawRect(g, edjePart, current);
					break;
				}
		}
	}

	private void drawRect(Graphics g, JEdjePart edjePart,
			JEdjeDescription current) {
		JEdjeRel rel1 = current.getRel1();
		JEdjeRel rel2 = current.getRel2();
		
		int[] coords1 = parseRel(rel1);
		int[] coords2 = parseRel(rel2);
		
		g.fillRect(coords1[0], coords1[1]
               , coords2[0] - coords1[0], coords2[1] - coords1[1]);
	}

	private int[] parseRel(JEdjeRel rel) {
		int[] coords = new int[2];
		JEdjeTuple relative = rel.getRelative();
		JEdjeTuple offset 	= rel.getOffset();

		if (rel.getTo() == null) {
			coords[0] = (int) Math.floor(this.getWidth() * relative.getHorizontal() + offset.getHorizontal());
			coords[1] = (int) Math.floor(this.getHeight() * relative.getVetical() + offset.getVetical());
		} else {
			JEdjeDescription description = rel.getTo().getCurrent();
			if (description != null) {
				int[] pCoords1 = parseRel(description.getRel1());
				int[] pCoords2 = parseRel(description.getRel2());
				int wParent = pCoords2[0] - pCoords1[0];
				int hParent = pCoords2[1] - pCoords1[1];
				coords[0] = pCoords1[0] + (int) Math.floor(wParent * relative.getHorizontal() + offset.getHorizontal());
				coords[1] = pCoords1[1] + (int) Math.floor(hParent * relative.getVetical() + offset.getVetical());
			}
		}
		return coords;
	}

}
