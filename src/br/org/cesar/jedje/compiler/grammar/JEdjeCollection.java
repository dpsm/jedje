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
public class JEdjeCollection {
	
	private JEdjeImage[] images;
	private JEdjeGroup[] groups;
	
	public JEdjeCollection(JEdjeImage[] _images, JEdjeGroup[] _groups) {
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
	
	public static JEdjeGroup getGroup(JEdjeCollection _collection, String _group) {
		JEdjeGroup myGroup = null;
		
		JEdjeGroup[] groups = _collection.getGroups();
		for (int i = 0; i < groups.length; i++) {
			JEdjeGroup group = groups[i];
			if (group.getName().equals(_group)) {
				myGroup = group;
				break;
			}
		}
		
		return myGroup;
	}
	
	public static JEdjePart getPart(JEdjeGroup _group, String _part) {
		JEdjePart  myPart  = null;

		JEdjePart[] parts = _group.getParts();
		for (int i = 0; i < parts.length; i++) {
			JEdjePart part = parts[i];
			if (part.getName().equals(_part)) {
				myPart = part;
				break;
			}
		}
		
		return myPart;
	}
	
	public static JEdjeDescription getDescription(JEdjePart _part, String _description) {
		JEdjeDescription description = null;
		
		JEdjeDescription[] descriptions = _part.getDescriptions();
		for (int i = 0; i < descriptions.length; i++) {
			if (descriptions[i].getState().equals(_description)) {
				description = descriptions[i];
				break;
			}
		}
		
		return description;
	}
}
