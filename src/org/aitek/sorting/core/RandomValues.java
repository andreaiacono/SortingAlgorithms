package org.aitek.sorting.core;

public enum RandomValues {

	RANDOM("Random"), ALMOST_SORTED("Almost sorted"), REVERSE("Reverse"), FEW_VALUES("Few Values");

	private String value;

	RandomValues(String value) {

		this.value = value;
	}

	public String getValue() {

		return value;
	}
}
