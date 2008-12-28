package br.org.cesar.jedje.compiler.grammar;


public class JEdjePart {

	private String name;
	private int	   effect;
	private int	   type;
	
	private boolean repeateEvents;
	private boolean mouseEvents;
	
	private JEdjePart clipTo;
	
	private JEdjeDescription[] descriptions;
	
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
	
}
