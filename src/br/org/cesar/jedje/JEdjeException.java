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
package br.org.cesar.jedje;

/**
 *  JEdjeException class handles all exceptions of the JEdje API.
 * 
 * @author <a href="dpsmarques@yahoo.com">David Marques</a>
 */
public class JEdjeException extends Exception {
	
    // Constants -----------------------------------------------------
    
    // Attributes ----------------------------------------------------
	
	// Static --------------------------------------------------------
    
    // Constructors --------------------------------------------------

	/**
	 * Creates an instance of the JEdjeException associated
	 * to the specified error message.
	 * 
	 * @param _message error message.
	 */
	public JEdjeException(String _message) {
		super(_message);
	}
	
	// Public --------------------------------------------------------

	// X implementation ----------------------------------------------
    
    // Y overrides ---------------------------------------------------
    
    // Package protected ---------------------------------------------
    
    // Protected -----------------------------------------------------
    
    // Private -------------------------------------------------------

}
