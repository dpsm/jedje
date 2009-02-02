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

import br.org.cesar.jedje.compiler.grammar.JEdjePart;

public class JEdjeCanvasObject {

	// Constants -----------------------------------------------------

	// Attributes ----------------------------------------------------

	private JEdjePart part;
	private int		  x;
	private int		  y;
	private int		  width;
	private int		  height;
	
	// Static --------------------------------------------------------

	// Constructors --------------------------------------------------

	public JEdjeCanvasObject(JEdjePart _part) {
		this.part = _part;
	}

	// Public --------------------------------------------------------

	/**
	 * @return the part
	 */
	public JEdjePart getPart() {
		return part;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	// X implementation ----------------------------------------------

	// Y overrides ---------------------------------------------------

	// Package protected ---------------------------------------------

	// Protected -----------------------------------------------------

	// Private -------------------------------------------------------

}
