package br.org.cesar.jedje.compiler.grammar;

public class JEdjeGroup {
	
	private String name;
	private JEdjeTuple min;
	private JEdjeTuple max;
	
	private JEdjeProgram[] programs;
	private JEdjePart[]    parts;
	
	public JEdjeGroup(String _name, JEdjeTuple _min, JEdjeTuple _max,
			JEdjeProgram[] _programs, JEdjePart[] _parts) {
		this.programs = _programs;
		this.parts 	  = _parts;
		this.name	  = _name;
		this.min 	  = _min;
		this.max	  = _max;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the min
	 */
	public JEdjeTuple getMin() {
		return min;
	}

	/**
	 * @return the max
	 */
	public JEdjeTuple getMax() {
		return max;
	}

	/**
	 * @return the programs
	 */
	public JEdjeProgram[] getPrograms() {
		return programs;
	}

	/**
	 * @return the parts
	 */
	public JEdjePart[] getParts() {
		return parts;
	}
	
}
