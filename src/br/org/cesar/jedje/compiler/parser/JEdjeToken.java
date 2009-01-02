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
package br.org.cesar.jedje.compiler.parser;

/**
 *  JEdjeToken class wraps edje tokens to be parsed by the JEdjeParser.
 *  
 * @author <a href="dpsmarques@yahoo.com">David Marques</a>
 */
public class JEdjeToken {

    // Constants -----------------------------------------------------
    
	private static int index = 0x05;
	
	public static final int SPECIAL_WORD = 0x01;
	public static final int IDENTIFIER 	 = 0x02;
	public static final int INTEGER    	 = 0x03;
	public static final int FLOAT 	   	 = 0x04;
	
	/*COMMON TOKENS*/
	public static final JEdjeToken LEFT_KEY  	= new JEdjeToken(index++, "{");
	public static final JEdjeToken RIGHT_KEY 	= new JEdjeToken(index++, "}");
	public static final JEdjeToken COMMA 		= new JEdjeToken(index++, ",");
	public static final JEdjeToken SEMI_COLLON	= new JEdjeToken(index++, ";");
	public static final JEdjeToken COLLON 		= new JEdjeToken(index++, ":");
	
	/*LANGUAGE TOKENS*/
	public static final JEdjeToken IMAGES 		= new JEdjeToken(SPECIAL_WORD, "images");
	public static final JEdjeToken IMAGE  		= new JEdjeToken(SPECIAL_WORD, "image");
	public static final JEdjeToken COLLECTIONS 	= new JEdjeToken(SPECIAL_WORD, "collections");
	public static final JEdjeToken GROUP 	  	= new JEdjeToken(SPECIAL_WORD, "group");
	public static final JEdjeToken PARTS 	  	= new JEdjeToken(SPECIAL_WORD, "parts");
	public static final JEdjeToken PART 	  		= new JEdjeToken(SPECIAL_WORD, "part");
	public static final JEdjeToken DESCRIPTION	= new JEdjeToken(SPECIAL_WORD, "description");
	public static final JEdjeToken PROGRAMS 	  	= new JEdjeToken(SPECIAL_WORD, "programs");
	public static final JEdjeToken PROGRAM  		= new JEdjeToken(SPECIAL_WORD, "program");
	public static final JEdjeToken NAME 	  	  	= new JEdjeToken(SPECIAL_WORD, "name");
	public static final JEdjeToken MIN 	  	  	= new JEdjeToken(SPECIAL_WORD, "min");
	public static final JEdjeToken MAX 	  	  	= new JEdjeToken(SPECIAL_WORD, "max");
	public static final JEdjeToken TYPE 	  	  	= new JEdjeToken(SPECIAL_WORD, "type");
	public static final JEdjeToken EFFECT  	  	= new JEdjeToken(SPECIAL_WORD, "effect");
	public static final JEdjeToken MOUSE_EVENTS 	= new JEdjeToken(SPECIAL_WORD, "mouse_events");
	public static final JEdjeToken REPEAT_EVENTS = new JEdjeToken(SPECIAL_WORD, "repeat_events");
	public static final JEdjeToken CLIP_TO  	  	= new JEdjeToken(SPECIAL_WORD, "clip_to");
	public static final JEdjeToken COLOR_CLASS  	= new JEdjeToken(SPECIAL_WORD, "color_class");
	public static final JEdjeToken TEXT_CLASS  	= new JEdjeToken(SPECIAL_WORD, "text_class");
	public static final JEdjeToken STATE  	  	= new JEdjeToken(SPECIAL_WORD, "state");
	public static final JEdjeToken VISIBLE  	  	= new JEdjeToken(SPECIAL_WORD, "visible");
	public static final JEdjeToken ALIGN  	  	= new JEdjeToken(SPECIAL_WORD, "align");
	public static final JEdjeToken STEP  	  	= new JEdjeToken(SPECIAL_WORD, "step");
	public static final JEdjeToken ASPECT  	  	= new JEdjeToken(SPECIAL_WORD, "aspect");
	public static final JEdjeToken COLOR  	  	= new JEdjeToken(SPECIAL_WORD, "color");
	public static final JEdjeToken COLOR2  	  	= new JEdjeToken(SPECIAL_WORD, "color2");
	public static final JEdjeToken COLOR3  	  	= new JEdjeToken(SPECIAL_WORD, "color3");
	public static final JEdjeToken INHERIT  	  	= new JEdjeToken(SPECIAL_WORD, "inherit");
	public static final JEdjeToken RELATIVE 	  	= new JEdjeToken(SPECIAL_WORD, "relative");
	public static final JEdjeToken OFFSET 	  	= new JEdjeToken(SPECIAL_WORD, "offset");
	public static final JEdjeToken TO 	  		= new JEdjeToken(SPECIAL_WORD, "to");
	public static final JEdjeToken TO_X 	  		= new JEdjeToken(SPECIAL_WORD, "to_x");
	public static final JEdjeToken TO_Y 	  		= new JEdjeToken(SPECIAL_WORD, "to_y");
	public static final JEdjeToken NORMAL  		= new JEdjeToken(SPECIAL_WORD, "normal");
	public static final JEdjeToken TWEEN	  		= new JEdjeToken(SPECIAL_WORD, "tween");
	public static final JEdjeToken REL1	  		= new JEdjeToken(SPECIAL_WORD, "rel1");
	public static final JEdjeToken REL2	  		= new JEdjeToken(SPECIAL_WORD, "rel2");
	public static final JEdjeToken FILL	  		= new JEdjeToken(SPECIAL_WORD, "fill");
	public static final JEdjeToken ORIGIN  		= new JEdjeToken(SPECIAL_WORD, "origin");
	public static final JEdjeToken SIZE  		= new JEdjeToken(SPECIAL_WORD, "size");
	public static final JEdjeToken TEXT  		= new JEdjeToken(SPECIAL_WORD, "text");
	public static final JEdjeToken FONT  		= new JEdjeToken(SPECIAL_WORD, "font");
	public static final JEdjeToken FIT  			= new JEdjeToken(SPECIAL_WORD, "fit");
	public static final JEdjeToken SIGNAL  		= new JEdjeToken(SPECIAL_WORD, "signal");
	public static final JEdjeToken SOURCE  		= new JEdjeToken(SPECIAL_WORD, "source");
	public static final JEdjeToken ACTION  		= new JEdjeToken(SPECIAL_WORD, "action");
	public static final JEdjeToken TRANSITION	= new JEdjeToken(SPECIAL_WORD, "transition");
	public static final JEdjeToken AFTER  		= new JEdjeToken(SPECIAL_WORD, "after");
	public static final JEdjeToken BORDER  		= new JEdjeToken(SPECIAL_WORD, "border");
	
	public static final JEdjeToken EOF 			= new JEdjeToken(index++, "JEdje-END");
	
    // Attributes ----------------------------------------------------
	
	private String value;
	private int	   type;
	
	// Static --------------------------------------------------------
    
	public static JEdjeToken getToken(String _value) {
		JEdjeToken token = null;
		if (IMAGES.getValue().equals(_value)) {
			token = IMAGES;
		} else
		if (IMAGE.getValue().equals(_value)) {
			token = IMAGE;
		} else
		if (COLLECTIONS.getValue().equals(_value)) {
			token = COLLECTIONS;
		} else
		if (GROUP.getValue().equals(_value)) {
			token = GROUP;
		} else
		if (PARTS.getValue().equals(_value)) {
			token = PARTS;
		} else
		if (PART.getValue().equals(_value)) {
			token = PART;
		} else
		if (DESCRIPTION.getValue().equals(_value)) {
			token = DESCRIPTION;
		} else
		if (PROGRAMS.getValue().equals(_value)) {
			token = PROGRAMS;
		} else
		if (PROGRAM.getValue().equals(_value)) {
			token = PROGRAM;
		} else
		if (NAME.getValue().equals(_value)) {
			token = NAME;
		} else
		if (MIN.getValue().equals(_value)) {
			token = MIN;
		} else
		if (MAX.getValue().equals(_value)) {
			token = MAX;
		} else
		if (TYPE.getValue().equals(_value)) {
			token = TYPE;
		} else
		if (MOUSE_EVENTS.getValue().equals(_value)) {
			token = MOUSE_EVENTS;
		} else
		if (REPEAT_EVENTS.getValue().equals(_value)) {
			token = REPEAT_EVENTS;
		} else
		if (CLIP_TO.getValue().equals(_value)) {
			token = CLIP_TO;
		} else
		if (COLOR_CLASS.getValue().equals(_value)) {
			token = COLOR_CLASS;
		} else
		if (TEXT_CLASS.getValue().equals(_value)) {
			token = TEXT_CLASS;
		} else
		if (STATE.getValue().equals(_value)) {
			token = STATE;
		} else
		if (VISIBLE.getValue().equals(_value)) {
			token = VISIBLE;
		} else
		if (ALIGN.getValue().equals(_value)) {
			token = ALIGN;
		} else
		if (STEP.getValue().equals(_value)) {
			token = STEP;
		} else
		if (ASPECT.getValue().equals(_value)) {
			token = ASPECT;
		} else
		if (COLOR.getValue().equals(_value)) {
			token = COLOR;
		} else
		if (COLOR2.getValue().equals(_value)) {
			token = COLOR2;
		} else
		if (COLOR3.getValue().equals(_value)) {
			token = COLOR3;
		} else
		if (INHERIT.getValue().equals(_value)) {
			token = INHERIT;
		} else			
		if (RELATIVE.getValue().equals(_value)) {
			token = RELATIVE;
		} else
		if (OFFSET.getValue().equals(_value)) {
			token = OFFSET;
		} else
		if (TO.getValue().equals(_value)) {
			token = TO;
		} else
		if (TO_X.getValue().equals(_value)) {
			token = TO_X;
		} else
		if (TO_Y.getValue().equals(_value)) {
			token = TO_Y;
		} else
		if (NORMAL.getValue().equals(_value)) {
			token = NORMAL;
		} else			
		if (TWEEN.getValue().equals(_value)) {
			token = TWEEN;
		} else
		if (REL1.getValue().equals(_value)) {
			token = REL1;
		} else
		if (REL2.getValue().equals(_value)) {
			token = REL2;
		} else
		if (FILL.getValue().equals(_value)) {
			token = FILL;
		} else
		if (ORIGIN.getValue().equals(_value)) {
			token = ORIGIN;
		} else
		if (SIZE.getValue().equals(_value)) {
			token = SIZE;
		} else
		if (TEXT.getValue().equals(_value)) {
			token = TEXT;
		} else
		if (FONT.getValue().equals(_value)) {
			token = FONT;
		} else
		if (FIT.getValue().equals(_value)) {
			token = FIT;
		} else
		if (SIGNAL.getValue().equals(_value)) {
			token = SIGNAL;
		} else
		if (SOURCE.getValue().equals(_value)) {
			token = SOURCE;
		} else
		if (ACTION.getValue().equals(_value)) {
			token = ACTION;
		} else
		if (TRANSITION.getValue().equals(_value)) {
			token = TRANSITION;
		} else
		if (AFTER.getValue().equals(_value)) {
			token = AFTER;
		} else
		if (BORDER.getValue().equals(_value)) {
			token = BORDER;
		} else
		if (EFFECT.getValue().equals(_value)) {
			token = EFFECT;
		} else {
			token = new JEdjeToken(IDENTIFIER, _value);
		}
		return token;
	}
	
    // Constructors --------------------------------------------------
	
	public JEdjeToken(int _type, String _value) {
		this.value = _value;
		this.type  = _type;
	}
	
	// Public --------------------------------------------------------

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		switch (this.type) {
			case SPECIAL_WORD:
				buffer.append("SPECIAL_WORD");
			break;
			case IDENTIFIER:
				buffer.append("IDENTIFIER");
			break;
			case INTEGER:
				buffer.append("INTEGER");
			break;
			case FLOAT:
				buffer.append("FLOAT");
			break;
		}
		buffer.append("] ");
		buffer.append(this.getValue());
		return buffer.toString();
	}
	
	// X implementation ----------------------------------------------
    
    // Y overrides ---------------------------------------------------
    
    // Package protected ---------------------------------------------
    
    // Protected -----------------------------------------------------
    
    // Private -------------------------------------------------------
	
}
