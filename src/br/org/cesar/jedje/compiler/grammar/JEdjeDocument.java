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
	
}
