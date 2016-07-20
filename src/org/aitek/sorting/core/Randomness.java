package org.aitek.sorting.core;

public enum Randomness {

	RANDOM("Random"), ALMOST_SORTED("Almost sorted"), ALREADY_SORTED("Already sorted"), REVERSE("Reverse"), FEW_VALUES("Few Values");

	private String value;

	Randomness(String value) {
		this.value = value;
	}

    @Override
	public String toString() {
		return value;
	}

    public static Randomness get(String value) {
        for (Randomness randomness: Randomness.values()){
            if (randomness.toString().equals(value)) return randomness;
        }
        return null;
    }
}
