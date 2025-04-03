package com.kimga.kimga_chat.services;

import java.util.Arrays;

public enum Role {
    USER("ROLE_USER"),
    CONSULTANT("ROLE_CONSULTANT");

    private String code;

    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Role fromCode(String code) {
        return Arrays.stream(Role.values())
                .filter(role -> role.code.equals(code))
                .findFirst()
                .orElseThrow();
    }
}
