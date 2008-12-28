package br.org.cesar.jedje.compiler.grammar;

import br.org.cesar.jedje.compiler.JEdjeException;

public class JEdjeDescription {
	
	private static final int IMAGE 	 = 0x00;
	private static final int RECT  	 = 0x01;
	private static final int TEXT  	 = 0x02;
	private static final int SWALLOW = 0x03;
	private static final int NONE 	 = 0x04;
	
	private String name;
	private int index;
	private boolean visible;
	private JEdjeTuple align;
	private JEdjeTuple min;
	private JEdjeTuple max;
	private JEdjePart inherit;
	private JEdjeColor color;
	private JEdjeRel rel1;
	private JEdjeRel rel2;
	
	public JEdjeDescription(String _name, boolean _visible, JEdjeTuple _align,
			JEdjeTuple _min, JEdjeTuple _max, JEdjePart _inherit, JEdjeColor _color,
				JEdjeRel _rel1, JEdjeRel _rel2) {
		this.name 	 = _name;
		this.visible = _visible;
		this.align 	 = _align;
		this.min 	 = _min;
		this.max 	 = _max;
		this.inherit = _inherit;
		this.color	 = _color;
		this.rel1	 = _rel1;
		this.rel2	 = _rel2;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
		return align;
	}

	/**
	 * @return the min
	 */
	public JEdjeTuple getMin() {
		return min;
	}

	/**
	 * @return the max
	 */
	public JEdjeTuple getMax() {
		return max;
	}

	/**
	 * @return the inherit
	 */
	public JEdjePart getInherit() {
		return inherit;
	}

	/**
	 * @return the color
	 */
	public JEdjeColor getColor() {
		return color;
	}

	/**
	 * @return the rel1
	 */
	public JEdjeRel getRel1() {
		return rel1;
	}

	/**
	 * @return the rel2
	 */
	public JEdjeRel getRel2() {
		return rel2;
	}

	public static int parseType(String value) throws JEdjeException {
		if (value.equals("IMAGE")) {
			return IMAGE;
		} else
		if (value.equals("RECT")) {
			return RECT;
		} else
		if (value.equals("TEXT")) {
			return TEXT;
		} else
		if (value.equals("SWALLOW")) {
			return SWALLOW;
		} else
		if (value.equals("NONE")) {
			return NONE;
		}	
		throw new JEdjeException("Unsupported part type.");
	}

	public static int parseEffect(String value) {
		return 0;
	}
	
}
