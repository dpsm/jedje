package br.org.cesar.jedje.compiler.grammar;

import br.org.cesar.jedje.compiler.JEdjeException;

public class JEdjeImage {
	
	public static final int LOSSY = 0x00;
	public static final int COMP  = 0x01;
	public static final int RAW   = 0x02;
	
	private String name;
	private int storage;
	private int level;
	
	public JEdjeImage(String _name, int _storage, int _level) {
		this.name 	 = _name;
		this.storage = _storage;
		this.level	 = _level;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the storage
	 */
	public int getStorage() {
		return storage;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	public static int parseStorage(String value) throws JEdjeException {
		if (value.equals("COMP")) {
			return COMP;
		} else
		if (value.equals("RAW")) {
			return RAW;
		} else
		if (value.equals("LOSSY")) {
			return LOSSY;
		}
		
		throw new JEdjeException("Invalid storage value.");
	}
}
