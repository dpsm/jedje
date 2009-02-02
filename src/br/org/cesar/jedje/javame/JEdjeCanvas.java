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
import java.io.InputStream;
import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import br.org.cesar.jedje.JEdjeException;
import br.org.cesar.jedje.compiler.grammar.JEdjeCollection;
import br.org.cesar.jedje.compiler.grammar.JEdjeColor;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescription;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescriptionImage;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescriptionText;
import br.org.cesar.jedje.compiler.grammar.JEdjeGroup;
import br.org.cesar.jedje.compiler.grammar.JEdjeImage;
import br.org.cesar.jedje.compiler.grammar.JEdjePart;
import br.org.cesar.jedje.compiler.grammar.JEdjeRel;
import br.org.cesar.jedje.compiler.grammar.JEdjeTuple;
import br.org.cesar.jedje.compiler.parser.JEdjeParser;
import br.org.cesar.jedje.compiler.parser.JEdjeScanner;

/**
 *  JEdjeCanvas class is an implementation of an Edje surface for
 *  Java ME applications.
 *  <br>
 *  It will draw the specified group from the specified collection.
 *  
 * @author <a href="dpsmarques@yahoo.com">David Marques</a>
 */
public class JEdjeCanvas extends Canvas {
	
	// Constants -----------------------------------------------------
    
	// Attributes ----------------------------------------------------
	
	private JEdjeCollection collection;
	private JEdjeGroup 	  	group;
	private Vector			stack;
	
	// Static --------------------------------------------------------
    
    // Constructors --------------------------------------------------

	public JEdjeCanvas(String _edje, String _group) throws JEdjeException {
		InputStream  stream  = this.getClass().getResourceAsStream(_edje);
		if (stream == null) {
			throw new JEdjeException("Unable to load " + _edje + " edje file.");
		}
		
		try {
			JEdjeScanner  scanner  = new JEdjeScanner(stream);
			JEdjeParser   parser   = new JEdjeParser(scanner);
			this.collection = parser.parseDocument();
			this.loadEdjeGroup(_group);
		} catch (IOException e) {
			throw new JEdjeException("Unable to parse " + _edje + " edje file.");
		}
		this.stack = new Vector();
	}
	
	// Public --------------------------------------------------------

	/**
	 * @return the collection
	 */
	public JEdjeCollection getDataCollection() {
		return collection;
	}

	// X implementation ----------------------------------------------
    
    // Y overrides ---------------------------------------------------
    
    // Package protected ---------------------------------------------
    
    // Protected -----------------------------------------------------
    
	protected final void paint(Graphics g) {
		JEdjePart[] parts = this.group.getParts();
		
		int color = g.getColor();
		Font font = g.getFont();
		
		this.stack.removeAllElements();
		for (int i = 0; i < parts.length; i++) {
			JEdjeCanvasObject object = new JEdjeCanvasObject(parts[i]);
			this.drawPart(g, object);
			this.stack.addElement(object);
			g.setColor(color);
			g.setFont(font);
		}
		this.paintImpl(g);
	}

	protected void paintImpl(Graphics g) {
		
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Canvas#pointerPressed(int, int)
	 */
	protected void pointerPressed(int x, int y) {
		JEdjeCanvasObject obj = getSource(x, y);
		if (obj != null) {
			this.pointerPressedEvent(obj.getPart(), x - obj.getX(), y - obj.getY());
		} else {			
			super.pointerPressed(x, y);
		}
	}

	/**
	 * Called when a pointer press event occurs on the part.
	 * 
	 * @param part source part.
	 * @param x relative to part position.
	 * @param y relative to part position.
	 */
	protected void pointerPressedEvent(JEdjePart part, int x, int y) {
		
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Canvas#pointerReleased(int, int)
	 */
	protected void pointerReleased(int x, int y) {
		JEdjeCanvasObject obj = getSource(x, y);
		if (obj != null) {
			this.pointerReleasedEvent(obj.getPart(), x - obj.getX(), y - obj.getY());
		} else {			
			super.pointerReleased(x, y);
		}
	}

	/**
	 * Called when a pointer release event occurs on the part.
	 * 
	 * @param part source part.
	 * @param x relative to part position.
	 * @param y relative to part position.
	 */
	protected void pointerReleasedEvent(JEdjePart part, int x, int y) {
		
	}
	
	// Private -------------------------------------------------------
	
	private JEdjeCanvasObject getSource(int x, int y) {
		JEdjeCanvasObject target = null;
		int size = this.stack.size();
		for (int i = size - 1; i >= 0x00; i--) {
			JEdjeCanvasObject obj = (JEdjeCanvasObject) this.stack.elementAt(i);
			if (x >= obj.getX() && x <= obj.getX() + obj.getWidth()&&
				y >= obj.getY() && y <= obj.getY() + obj.getHeight()) {
				target = obj;
				break;
			}
		}
		
		return target;
	}
	
	private void loadEdjeGroup(String name) throws JEdjeException {
		JEdjeGroup[] groups = this.collection.getGroups();
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
	
	private void drawPart(Graphics g, JEdjeCanvasObject object) {
		JEdjePart part = object.getPart();
		JEdjeDescription current = part.getCurrent();
		
		if (current != null && current.isVisible()) {
			JEdjeColor color = current.getColor();
			if (color != null) {
				g.setColor(color.getR(), color.getG(), color.getB());
			}
			
			JEdjeRel rel1 = current.getRel1();
			JEdjeRel rel2 = current.getRel2();
			switch (part.getType()) {
				case JEdjePart.IMAGE:
					drawImage(g, object, current, rel1, current.getAlign());
				break;
				case JEdjePart.RECT:
					drawRect(g, object, current, rel1, rel2, current.getAlign());
				break;
				case JEdjePart.TEXT:
					drawText(g, object, current, rel1, current.getAlign());
				break;
			}
		}
	}

	private void drawText(Graphics g, JEdjeCanvasObject object, JEdjeDescription current, JEdjeRel rel1, JEdjeTuple align) {
		JEdjeDescriptionText text = current.getText();
		if (text == null) {
			return;
		}
		
		Font font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
		int  size = text.getSize();

		int[] sizes = {Font.SIZE_MEDIUM, Font.SIZE_LARGE};
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
		
		object.setX(coords1[0]);
		object.setY(coords1[1]);
		object.setWidth(font.stringWidth(text.getValue()));
		object.setHeight(font.getSize());
		g.drawString(text.getValue(), object.getX(), object.getY(), 0x00);
	}

	private void drawImage(Graphics g, JEdjeCanvasObject object, JEdjeDescription current, JEdjeRel rel1, JEdjeTuple align) {
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
				object.setX(coords1[0]);
				object.setY(coords1[1]);
				object.setWidth(img.getWidth());
				object.setHeight(img.getHeight());
				g.drawImage(img, object.getX(), object.getY(), 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void drawRect(Graphics g, JEdjeCanvasObject object, JEdjeDescription current, JEdjeRel rel1, JEdjeRel rel2, JEdjeTuple align) {
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
		object.setX(coords1[0]);
		object.setY(coords1[1]);
		object.setWidth(coords2[0] - coords1[0]);
		object.setHeight(coords2[1] - coords1[1]);
		g.fillRect(object.getX(), object.getY(), object.getWidth(), object.getHeight());
		
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
