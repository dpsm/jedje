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

public class JEdjeAction {
	
	private String param1;
	private String param2;
	private int type;
	
	public JEdjeAction(int _type, String _param1, String _param2) {
		this.param1 = _param1;
		this.param2 = _param2;
		this.type	= _type;
	}

	/**
	 * @return the param1
	 */
	public String getParam1() {
		return param1;
	}

	/**
	 * @return the param2
	 */
	public String getParam2() {
		return param2;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
}
