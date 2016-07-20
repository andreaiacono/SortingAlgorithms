package org.aitek.sorting.core;

import java.util.Arrays;

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
        return Arrays.stream(Randomness.values()).filter(val -> val.toString().equals(value)).findAny().orElse(null);
    }
}
