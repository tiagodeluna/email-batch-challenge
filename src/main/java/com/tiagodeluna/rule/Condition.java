package com.tiagodeluna.rule;

/**
 * Specifies a condition to be satisfied.
 * 
 * @author Tiago Luna
 */
public class Condition {

	private SignType signal;
	private int value;

	public Condition(SignType signal, int value) {
		super();
		this.signal = signal;
		this.value = value;
	}

	public SignType getSignal() {
		return signal;
	}

	public void setSignal(SignType signal) {
		this.signal = signal;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Verifies if a user satisfies a specific condition.
	 * 
	 * @param value
	 * @return
	 */
	public boolean isSatisfied(int value) {
		if (signal.equals(SignType.EQUALS)) {
			return value == this.value;
		}
		else if (signal.equals(SignType.GREATER_THAN)) {
			return value > this.value;
		} 
		else if (signal.equals(SignType.LESS_THAN)) {
			return value < this.value;
		} 
		
		return false;
	}
}
