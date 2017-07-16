package com.lychee.amz.analytics.features.authentication.filter;


import com.lychee.amz.analytics.features.authentication.exception.BadCredentialException;
import com.lychee.amz.analytics.features.authentication.help.JwtHelp;
import com.lychee.amz.analytics.features.authentication.model.AuthUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.codec.Base64;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Collections;

/**
 * parseBasicCredential http basic credential to get user name and password
 */
public class HttpCredentialParser {

    public static UsernamePasswordAuthenticationToken parseBasicCredential(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization) || ! authorization.startsWith("Basic ")) {
            throw new BadCredentialException();
        }

        String token = authorization.substring(5).trim();
        String userPassword;
        try {
            userPassword = new String(Base64.decode(token.getBytes(Charset.forName("US-ASCII"))));
        } catch (Exception ex) {
            throw new BadCredentialException();
        }


        if (!userPassword.contains(":")) {
            throw new BadCredentialException();
        }

        String[] parts = userPassword.split(":");
        String user = parts[0];
        String password=parts[1];
        return new UsernamePasswordAuthenticationToken(user, password, Collections.<GrantedAuthority>emptyList());
    }


    public static UsernamePasswordAuthenticationToken parseJwtToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization) || ! authorization.startsWith("Bearer ")) {
            throw new BadCredentialException();
        }

        String token = authorization.substring(6).trim();
        AuthUser authUser = JwtHelp.parseToken(token);

        return new UsernamePasswordAuthenticationToken(authUser, null,authUser.getAuthorities());
    }
}
