package com.lychee.amz.analytics.utl;


import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthorityUtl {
    public static  List<String> getRolesFromAuthorities(Collection<GrantedAuthority> authorities) {
        List<String> roles = new ArrayList<>();
        authorities.stream().forEach(g -> roles.add(g.getAuthority()));

        return roles;
    }
}
