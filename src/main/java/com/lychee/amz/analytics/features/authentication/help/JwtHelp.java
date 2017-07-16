package com.lychee.amz.analytics.features.authentication.help;


import com.lychee.amz.analytics.advice.ConfigurationAdvice;
import com.lychee.amz.analytics.features.account.model.Roles;
import com.lychee.amz.analytics.features.authentication.model.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;
public class JwtHelp {

    public static String createJwtToken(AuthUser authUser) {
        String token = Jwts.builder()
                .setSubject("users/" + authUser.getId())
                .claim("email", authUser.getUsername())
                .setId(authUser.getId().toString())
                .claim("displayName", authUser.getDisplayName())
                .claim("authorities", authUser.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * ConfigurationAdvice.singleton.jwtExpirationInMinutes))
                .signWith(SignatureAlgorithm.HS512, ConfigurationAdvice.singleton.jwtSigningSecret)
                .compact();


        return token;
    }


    public static AuthUser parseToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(ConfigurationAdvice.singleton.jwtSigningSecret).parseClaimsJws(token).getBody();
        String email = claims.get("email").toString();
        Integer id = Integer.parseInt(claims.getId());
        String displayName = claims.get("displayName").toString();
        List<Map<String, String>> authorities = (List<Map<String, String>>) claims.get("authorities");


        AuthUser authUser = new AuthUser(email, "NA", AuthorityUtils.createAuthorityList(getRolesFromAuthority(authorities)));
        authUser.setId(id);
        authUser.setDisplayName(displayName);
        return authUser;
    }


    private static String[] getRolesFromAuthority(List<Map<String, String>> authorities) {
        if (authorities == null || authorities.size() == 0) {
            return new String[]{Roles.anonymous};
        }


        List<String> roles = new ArrayList<>();
        authorities.stream().forEach(a -> roles.add(a.get("authority")));
        return roles.toArray(new String[0]);
    }
}