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
package br.org.cesar.jedje.javame;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import br.org.cesar.jedje.JEdjeException;
import br.org.cesar.jedje.compiler.grammar.JEdjeColor;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescription;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescriptionImage;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescriptionText;
import br.org.cesar.jedje.compiler.grammar.JEdjeDocument;
import br.org.cesar.jedje.compiler.grammar.JEdjeGroup;
import br.org.cesar.jedje.compiler.grammar.JEdjeImage;
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
	
	/**
	 * @return the document
	 */
	public JEdjeDocument getDocument() {
		return document;
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

	protected final void paint(Graphics g) {
		JEdjePart[] parts = this.group.getParts();
		for (int i = 0; i < parts.length; i++) {
			this.drawPart(g, parts[i]);
		}
	}

	private void drawPart(Graphics g, JEdjePart edjePart) {
		JEdjeDescription current = edjePart.getCurrent();
		
		if (current != null && current.isVisible()) {
			JEdjeColor color = current.getColor();
			if (color != null) {
				g.setColor(color.getR(), color.getG(), color.getB());
			}
			
			JEdjeRel rel1 = current.getRel1();
			JEdjeRel rel2 = current.getRel2();
			switch (edjePart.getType()) {
				case JEdjePart.IMAGE:
					drawImage(g, current, rel1, current.getAlign());
				break;
				case JEdjePart.RECT:
					drawRect(g, current, rel1, rel2, current.getAlign());
				break;
				case JEdjePart.TEXT:
					drawText(g, current, rel1, current.getAlign());
				break;
			}
		}
	}

	private void drawText(Graphics g, JEdjeDescription current, JEdjeRel rel1, JEdjeTuple align) {
		JEdjeDescriptionText text = current.getText();
		if (text == null) {
			return;
		}
		
		Font font = Font.getDefaultFont();
		int  size = text.getSize();

		int[] sizes = {Font.SIZE_SMALL, Font.SIZE_MEDIUM, Font.SIZE_LARGE};
		for (int i = 0; i < sizes.length; i++) {
			Font f = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, sizes[i]);
			if (f.getSize() > size) {
				break;
			} else {
				font = f;
			}
		}

		JEdjeColor color = current.getColor();
		if (color != null) {
			g.setColor(color.getR(), color.getG(), color.getB());
		}
		
		if (font != null) {
			g.setFont(font);
		}
		
		int[] coords1 = parseRel(rel1);
		if (align != null) {
			float hAlign = align.getHorizontal();
			float vAlign = align.getVetical();
			
			coords1[0] -= font.stringWidth(text.getValue()) * hAlign;
			coords1[1] -= font.getSize() * vAlign;
		}
		
		g.drawString(text.getValue(), coords1[0], coords1[1], 0x00);
	}

	private void drawImage(Graphics g, JEdjeDescription current, JEdjeRel rel1, JEdjeTuple align) {
		JEdjeDescriptionImage image = current.getImage();
		if (image == null) {
			return;
		}
		
		JEdjeImage normal = image.getNormal();
		if (normal != null) {
			try {
				Image img = Image.createImage(normal.getName());
				if (rel1 == null) {
					rel1 = new JEdjeRel(new JEdjeTuple(0, 0), new JEdjeTuple(0, 0), null, null, null);
				}
				int[] coords1 = this.parseRel(rel1);
				if (align != null) {
					float hAlign = align.getHorizontal();
					float vAlign = align.getVetical();
					coords1[0] -= img.getWidth() * hAlign;
					coords1[1] -= img.getHeight() * vAlign;
				}
				g.drawImage(img, coords1[0], coords1[1], 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void drawRect(Graphics g, JEdjeDescription current, JEdjeRel rel1, JEdjeRel rel2, JEdjeTuple align) {
		if (rel1 == null) {
			rel1 = new JEdjeRel(new JEdjeTuple(0, 0), new JEdjeTuple(0, 0), null, null, null);
		}
		
		if (rel2 == null) {
			rel2 = new JEdjeRel(new JEdjeTuple(1, 1), new JEdjeTuple(0, 0), null, null, null);
		}
		
		int[] coords1 = parseRel(rel1);
		int[] coords2 = parseRel(rel2);
		
		if (align != null) {
			float hAlign = align.getHorizontal();
			float vAlign = align.getVetical();
			coords1[0] -= (coords2[0] - coords1[0]) * hAlign;
			coords2[0] -= (coords2[0] - coords1[0]) * hAlign;
			
			coords1[1] -= (coords2[1] - coords1[1]) * vAlign;
			coords2[1] -= (coords2[1] - coords1[1]) * vAlign;
		}
		
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
