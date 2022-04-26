package com.lubian.grupparbete.security;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    TODO_READ("todo:read"),
    TODO_WRITE("todo:write");

    private String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
