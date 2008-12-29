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


public class JEdjePart {

	public static final int IMAGE 	 = 0x00;
	public static final int RECT  	 = 0x01;
	public static final int TEXT  	 = 0x02;
	public static final int SWALLOW  = 0x03;
	public static final int NONE 	 = 0x04;
	
	private String name;
	private int	   effect;
	private int	   type;
	
	private boolean repeateEvents;
	private boolean mouseEvents;
	
	private JEdjePart clipTo;
	
	private JEdjeDescription[] descriptions;
	private JEdjeDescription   current;
	
	public static JEdjeDescription getDescription(JEdjePart edjePart, String name) {
		JEdjeDescription description = null;
		JEdjeDescription[] descriptions = edjePart.getDescriptions();
		for (int i = 0; i < descriptions.length; i++) {
			if (descriptions[i].getName().equals("default")) {
				description = descriptions[i];
				break;
			}
		}
		return description;
	}
	
	public JEdjePart(String _name, int _type, int _effect
			, boolean _repeateEvents, boolean _mouseEvents,
				JEdjePart _clipTo, JEdjeDescription[] _descriptions) {
		this.name 		   = _name;
		this.type 		   = _type;
		this.effect 	   = _effect;
		this.repeateEvents = _repeateEvents;
		this.mouseEvents   = _mouseEvents;
		this.clipTo 	   = _clipTo;
		this.descriptions  = _descriptions;
		
		this.setCurrent(JEdjePart.getDescription(this, "default"));
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the effect
	 */
	public int getEffect() {
		return effect;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the repeateEvents
	 */
	public boolean isRepeateEvents() {
		return repeateEvents;
	}

	/**
	 * @return the mouseEvents
	 */
	public boolean isMouseEvents() {
		return mouseEvents;
	}

	/**
	 * @return the clipTo
	 */
	public JEdjePart getClipTo() {
		return clipTo;
	}

	/**
	 * @return the descriptions
	 */
	public JEdjeDescription[] getDescriptions() {
		return descriptions;
	}

	/**
	 * @return the current
	 */
	public JEdjeDescription getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(JEdjeDescription current) {
		this.current = current;
	}
	
}
