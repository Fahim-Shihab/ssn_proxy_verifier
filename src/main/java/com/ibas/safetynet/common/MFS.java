package com.ibas.safetynet.common;

import java.util.Objects;

public enum MFS {
    ROCKET((short) 1, "rocket"),
    BKASH((short) 2, "bkash"),
    MCASH((short) 4, "mcash"),
    UPAY((short) 5, "upay"),
    NAGAD((short) 7, "nagad"),
    ;

    private final Short value;
    private final String name;

    MFS(Short value, String name) {
        this.value = value;
        this.name = name;
    }

    public Short getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static MFS getByValue(Short value) {
        for (MFS e : values()) {
            if (Objects.equals(e.value, value)) {
                return e;
            }
        }
        return null;
    }

    public static MFS getByName(String name) {
        for (MFS e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
