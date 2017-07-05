package com.lychee.amz.analytics.features.auth.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class TokenUser extends User {
    public TokenUser(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
    }
}
