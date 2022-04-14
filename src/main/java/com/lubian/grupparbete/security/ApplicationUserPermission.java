package com.lubian.grupparbete.security;

public enum ApplicationUserPermission {
    DUMMY_READ("admin:read"),
    DUMMY_WRITE("admin:write"),
    GUEST_READ("todo:read"),
    GUEST_WRITE("todo:write");


    private String permission;

    ApplicationUserPermission(String s) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
