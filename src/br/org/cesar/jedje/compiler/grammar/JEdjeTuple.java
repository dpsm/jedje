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

public class JEdjeTuple {
	
	private float horizontal;
	private float vetical;
	
	public JEdjeTuple(float _horizontal, float _vertical) {
		this.horizontal = _horizontal;
		this.vetical	= _vertical;
	}

	/**
	 * @return the horizontal
	 */
	public float getHorizontal() {
		return horizontal;
	}

	/**
	 * @return the vetical
	 */
	public float getVetical() {
		return vetical;
	}
	
}
