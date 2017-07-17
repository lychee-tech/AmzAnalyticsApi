package com.lychee.amz.analytics.features.authentication.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthUser extends User {
    private Integer id;
    private String displayName;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getDisplayName() {
        return displayName;
    }


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
