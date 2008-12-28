package br.org.cesar.jedje.compiler.grammar;

public class JEdjeTuple {
	
	private float horizontal;
	private float vetical;
	
	public JEdjeTuple(float _horizontal, float _vertical) {
		this.horizontal = _horizontal;
		this.vetical	= _vertical;
	}

	/**
	 * @return the horizontal
	 */
	public float getHorizontal() {
		return horizontal;
	}

	/**
	 * @return the vetical
	 */
	public float getVetical() {
		return vetical;
	}
	
}
