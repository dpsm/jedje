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
public class JEdjeDescriptionImage {
	
	private JEdjeImage normal;
	private JEdjeImage tween;
	private int borderTop;
	private int borderLeft;
	private int borderRight;
	private int borderBottom;
	
	public JEdjeDescriptionImage(JEdjeImage _normal, JEdjeImage _tween,
			int _borderTop, int _borderLeft, int _borderRight, int _borderBottom) {
		this.normal 	  = _normal;
		this.tween 		  = _tween;
		this.borderTop    = _borderTop;
		this.borderLeft   = _borderLeft;
		this.borderRight  = _borderRight;
		this.borderBottom = _borderBottom;
	}

	/**
	 * @return the normal
	 */
	public JEdjeImage getNormal() {
		return normal;
	}

	/**
	 * @return the tween
	 */
	public JEdjeImage getTween() {
		return tween;
	}

	/**
	 * @return the borderTop
	 */
	public int getBorderTop() {
		return borderTop;
	}

	/**
	 * @return the borderLeft
	 */
	public int getBorderLeft() {
		return borderLeft;
	}

	/**
	 * @return the borderRight
	 */
	public int getBorderRight() {
		return borderRight;
	}

	/**
	 * @return the borderBottom
	 */
	public int getBorderBottom() {
		return borderBottom;
	}
	
}
