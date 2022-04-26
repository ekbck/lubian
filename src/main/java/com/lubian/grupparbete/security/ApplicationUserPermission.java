package com.lubian.grupparbete.security;

public enum ApplicationUserPermission {
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
