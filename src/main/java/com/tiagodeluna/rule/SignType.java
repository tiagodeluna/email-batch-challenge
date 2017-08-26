package com.tiagodeluna.rule;

public enum SignType {
	EQUALS("eq"),
	GREATER_THAN("gt"),
	LESS_THAN("lt");
	
	String value;

	private SignType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public static SignType fromValue(String value) {
		for (SignType tp : values()) {
			if (tp.getValue().equals(value)) {
				return tp;
			}
		}
		
		throw new IllegalArgumentException(String.format("SignType not found: %s", value));
	}
}
