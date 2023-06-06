package com.grocery_project.core.constant;

public enum RoleType {
    ADMIN("ADMIN"),
    USER("USER");

    public final String value;
    RoleType(String value) {
        this.value = value;
    }
}
