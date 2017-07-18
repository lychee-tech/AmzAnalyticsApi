package com.lychee.amz.analytics.utl;

import com.lychee.amz.analytics.features.account.model.Roles;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class AuthorityUtlTest {
    @Test
    public void testGetRolesFromAuthorities (){
        List<String> roles = AuthorityUtl.getRolesFromAuthorities(AuthorityUtils.createAuthorityList(Roles.admin,Roles.user));
        assertEquals(2, roles.size());
        assertTrue(roles.contains(Roles.admin));
        assertTrue(roles.contains(Roles.user));
    }
}
