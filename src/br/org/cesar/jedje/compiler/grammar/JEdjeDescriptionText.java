package br.org.cesar.jedje.compiler.grammar;

public class JEdjeDescriptionText {
	
	private String value;
	private String font;
	private int    size;
	
	private JEdjeTuple align;
	private JEdjeTuple min;
	private JEdjeTuple fit;
	
	public JEdjeDescriptionText(String _value, String _font, int _size,
			JEdjeTuple _align, JEdjeTuple _min, JEdjeTuple _fit) {
		this.value = _value;
		this.font  = _font;
		this.size  = _size;
		this.align = _align;
		this.min   = _min;
		this.fit   = _fit;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the font
	 */
	public String getFont() {
		return font;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
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
	 * @return the fit
	 */
	public JEdjeTuple getFit() {
		return fit;
	}
	
}

