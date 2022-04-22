package com.lubian.grupparbete.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.lubian.grupparbete.security.ApplicationUserPermission.*;
public enum ApplicationUserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            USER_READ,
            USER_WRITE,
            TODO_READ,
            TODO_WRITE));

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    private final Set<ApplicationUserPermission> permissions;
    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

}
