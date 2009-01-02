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
package br.org.cesar.jedje.compiler.grammar;

import br.org.cesar.jedje.JEdjeException;

/**
 * @author <a href="dpsmarques@yahoo.com">David Marques</a>
 */
public class JEdjeDescription {
	
	private String state;
	private int index;
	private boolean visible;
	private JEdjeTuple align;
	private JEdjeTuple min;
	private JEdjeTuple max;
	private JEdjeDescription inherit;
	private JEdjeColor color;
	private JEdjeRel rel1;
	private JEdjeRel rel2;
	private JEdjeDescriptionImage image;
	private JEdjeDescriptionText text;
	
	public JEdjeDescription(String _state, boolean _visible, JEdjeTuple _align,
			JEdjeTuple _min, JEdjeTuple _max, JEdjeDescription _inherit, JEdjeColor _color,
				JEdjeDescriptionImage _image, JEdjeDescriptionText _text, JEdjeRel _rel1, JEdjeRel _rel2) {
		this.state 	 = _state;
		this.visible = _visible;
		this.align 	 = _align;
		this.min 	 = _min;
		this.max 	 = _max;
		this.inherit = _inherit;
		this.color	 = _color;
		this.rel1	 = _rel1;
		this.rel2	 = _rel2;
		this.image	 = _image;
		this.text	 = _text;
	}

	/**
	 * @return the name
	 */
	public String getState() {
		if (this.state == null && this.inherit != null) {
			return this.inherit.getState();
		}
		return state;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @return the align
	 */
	public JEdjeTuple getAlign() {
		if (this.align == null && this.inherit != null) {
			return this.inherit.getAlign();
		}
		return align;
	}

	/**
	 * @return the min
	 */
	public JEdjeTuple getMin() {
		if (this.min == null && this.inherit != null) {
			return this.inherit.getMin();
		}
		return min;
	}

	/**
	 * @return the max
	 */
	public JEdjeTuple getMax() {
		if (this.max == null && this.inherit != null) {
			return this.inherit.getMax();
		}
		return max;
	}

	/**
	 * @return the inherit
	 */
	public JEdjeDescription getInherit() {
		return inherit;
	}

	/**
	 * @return the color
	 */
	public JEdjeColor getColor() {
		if (this.color == null && this.inherit != null) {
			return this.inherit.getColor();
		}
		return color;
	}

	/**
	 * @return the rel1
	 */
	public JEdjeRel getRel1() {
		if (this.rel1 == null && this.inherit != null) {
			return this.inherit.getRel1();
		}
		return rel1;
	}

	/**
	 * @return the rel2
	 */
	public JEdjeRel getRel2() {
		if (this.rel2 == null && this.inherit != null) {
			return this.inherit.getRel2();
		}
		return rel2;
	}

	public static int parseType(String value) throws JEdjeException {
		if (value.equals("IMAGE")) {
			return JEdjePart.IMAGE;
		} else
		if (value.equals("RECT")) {
			return JEdjePart.RECT;
		} else
		if (value.equals("TEXT")) {
			return JEdjePart.TEXT;
		} else
		if (value.equals("SWALLOW")) {
			return JEdjePart.SWALLOW;
		} else
		if (value.equals("NONE")) {
			return JEdjePart.NONE;
		}	
		throw new JEdjeException("Unsupported part type.");
	}

	/**
	 * @return the image
	 */
	public JEdjeDescriptionImage getImage() {
		if (this.image == null && this.inherit != null) {
			return this.inherit.getImage();
		}
		return image;
	}

	/**
	 * @return the text
	 */
	public JEdjeDescriptionText getText() {
		return text;
	}

}
