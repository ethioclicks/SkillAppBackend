package com.ethioclicks.skilledApp.security.enums;

public enum RoleEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private final String roleName;

    private RoleEnum(String roleName) {
        this.roleName=roleName;
    }


    public String getRoleName() {
        return roleName;
    }
}
