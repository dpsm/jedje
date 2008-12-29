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

public class JEdjeDocument {
	
	private JEdjeImage[] images;
	private JEdjeGroup[] groups;
	
	public JEdjeDocument(JEdjeImage[] _images, JEdjeGroup[] _groups) {
		this.images = _images;
		this.groups = _groups;
	}

	/**
	 * @return the images
	 */
	public JEdjeImage[] getImages() {
		return images;
	}

	/**
	 * @return the groups
	 */
	public JEdjeGroup[] getGroups() {
		return groups;
	}
	
	public static JEdjePart getPart(JEdjeDocument _document, String _group, String _part) {
		JEdjeGroup myGroup = null;
		JEdjePart  myPart  = null;
		
		JEdjeGroup[] groups = _document.getGroups();
		for (int i = 0; i < groups.length; i++) {
			JEdjeGroup group = groups[i];
			if (group.getName().equals(_group)) {
				myGroup = group;
				break;
			}
		}
		
		if (myGroup != null) {			
			JEdjePart[] parts = myGroup.getParts();
			for (int i = 0; i < parts.length; i++) {
				JEdjePart part = parts[i];
				if (part.getName().equals(_part)) {
					myPart = part;
					break;
				}
			}
		}
		return myPart;
	}
	
	public static JEdjeDescription getDescription(JEdjeDocument _document, String _group, String _part, String _description) {
		JEdjeDescription description = null;
		JEdjePart  		 part  		 = null;
		
		part = JEdjeDocument.getPart(_document, _group, _part);
		if (part != null) {
			JEdjeDescription[] descriptions = part.getDescriptions();
			for (int i = 0; i < descriptions.length; i++) {
				if (descriptions[i].getName().equals(_description)) {
					description = descriptions[i];
					break;
				}
			}
		}
		return description;
	}
}
