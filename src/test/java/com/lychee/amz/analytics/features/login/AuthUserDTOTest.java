package com.lychee.amz.analytics.features.login;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lychee.amz.analytics.features.account.model.Roles;
import org.junit.Test;
import  static org.junit.Assert.*;

public class AuthUserDTOTest {
    @Test
    public void testSerializeAuthDTO() throws Exception {
        AuthUserDTO user = new AuthUserDTO();
        user.setDisplayName("wlin");
        user.setId(1);
        user.addRole(Roles.user);
        user.addRole(Roles.admin);
        user.addScope("shoe");
        user.addScope("amz keyword");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        String expected = "{\"id\":1,\"displayName\":\"wlin\",\"scopes\":[\"amz keyword\",\"shoe\"],\"roles\":[\"role_admin\",\"role_user\"]}";
        assertEquals(expected, json);
    }

}
