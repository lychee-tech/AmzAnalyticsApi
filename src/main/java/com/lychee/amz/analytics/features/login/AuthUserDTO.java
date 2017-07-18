package com.lychee.amz.analytics.features.login;


import java.util.Set;
import java.util.HashSet;

/**
 * user dto to be sent to ui
 */
public class AuthUserDTO {
    private Integer id;
    private String displayName;
    private Set<String> scopes =new HashSet<>();
    private Set<String> roles = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public AuthUserDTO setId(Integer id) {
        this.id = id;
        return this;
    }


    public String getDisplayName() {
        return displayName;
    }


    public AuthUserDTO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }


    public Set<String> getScopes() {
        return scopes;
    }


    public AuthUserDTO addScope(String scope) {
        scopes.add(scope.toLowerCase().trim());
        return this;
    }

    public AuthUserDTO setScopes(Set<String> scopes) {
        this.scopes = scopes;
        return this;
    }

    public Set<String> getRoles() {
        return roles;
    }


    public AuthUserDTO setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }

    public AuthUserDTO addRole(String role) {
        roles.add(role.toLowerCase().trim());
        return this;
    }
}
