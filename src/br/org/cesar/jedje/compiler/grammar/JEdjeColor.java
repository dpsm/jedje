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
public class JEdjeColor {

	private int r;
	private int g;
	private int b;
	private int a;
	
	public JEdjeColor(int _r, int _g, int _b, int _a) {
		this.r = _r;
		this.g = _g;
		this.b = _b;
		this.a = _a;
	}

	/**
	 * @return the r
	 */
	public int getR() {
		return r;
	}

	/**
	 * @return the g
	 */
	public int getG() {
		return g;
	}

	/**
	 * @return the b
	 */
	public int getB() {
		return b;
	}

	/**
	 * @return the a
	 */
	public int getA() {
		return a;
	}
	
}
