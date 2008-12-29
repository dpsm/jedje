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

public class JEdjeRel {
	
	private JEdjeTuple relative;
	private JEdjeTuple offset;
	
	private JEdjePart to;
	private JEdjePart to_x;
	private JEdjePart to_y;
	
	public JEdjeRel(JEdjeTuple _relative, JEdjeTuple _offset,
			JEdjePart _to, JEdjePart _to_x, JEdjePart _to_y) {
		this.relative = _relative;
		this.offset	  = _offset;
		this.to 	  = _to;
		this.to_x 	  = _to_x;
		this.to_y 	  = _to_y;
	}

	/**
	 * @return the relative
	 */
	public JEdjeTuple getRelative() {
		return relative;
	}

	/**
	 * @return the offset
	 */
	public JEdjeTuple getOffset() {
		return offset;
	}

	/**
	 * @return the to
	 */
	public JEdjePart getTo() {
		return to;
	}

	/**
	 * @return the to_x
	 */
	public JEdjePart getTo_x() {
		return to_x;
	}

	/**
	 * @return the to_y
	 */
	public JEdjePart getTo_y() {
		return to_y;
	}
	
}
