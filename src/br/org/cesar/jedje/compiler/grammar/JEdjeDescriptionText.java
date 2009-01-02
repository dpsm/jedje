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

/**
 * @author <a href="dpsmarques@yahoo.com">David Marques</a>
 */
public class JEdjeDescriptionText {
	
	private String value;
	private String font;
	private int    size;
	
	private JEdjeTuple min;
	private JEdjeTuple fit;
	
	public JEdjeDescriptionText(String _value, String _font, 
			int _size, JEdjeTuple _min, JEdjeTuple _fit) {
		this.value = _value;
		this.font  = _font;
		this.size  = _size;
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

