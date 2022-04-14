package com.lubian.grupparbete.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.lubian.grupparbete.security.ApplicationUserPermission.*;
public enum ApplicationUserRole {
    GUEST(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            DUMMY_WRITE,
            DUMMY_READ,
            GUEST_READ,
            GUEST_WRITE));

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    private final Set<ApplicationUserPermission> permissions;
    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

}
