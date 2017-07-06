package com.lychee.amz.analytics.features.authentication.help;


import com.lychee.amz.analytics.features.authentication.exception.BadCredentialException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.codec.Base64;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Collections;

/**
 * parse http basic credential to get user name and password
 */
public class HttpBasicCredentialParser {

    public static UsernamePasswordAuthenticationToken parse(HttpServletRequest request) {
        String basic = request.getHeader("Authorization");
        if (StringUtils.isBlank(basic) || ! basic.startsWith("Basic ")) {
            throw new BadCredentialException();
        }

        String code = basic.substring(5).trim();
        String userPassword;
        try {
            userPassword = new String(Base64.decode(code.getBytes(Charset.forName("US-ASCII"))));
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
}
