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

public class JEdjeProgram {
	
	private String name;
	private String signal;
	private String source;
	
	private JEdjeProgram after;
	private JEdjeAction  action;
	private JEdjePart 	 target;
	
	public JEdjeProgram(String _name, String _signal, String _source,
			JEdjePart _target, JEdjeAction _action, JEdjeProgram _after) {
		this.name 	= _name;
		this.signal = _signal;
		this.source = _source;
		this.target = _target;
		this.action = _action;
		this.after	= _after;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the signal
	 */
	public String getSignal() {
		return signal;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return the after
	 */
	public JEdjeProgram getAfter() {
		return after;
	}

	/**
	 * @return the action
	 */
	public JEdjeAction getAction() {
		return action;
	}

	/**
	 * @return the target
	 */
	public JEdjePart getTarget() {
		return target;
	}
	
}
