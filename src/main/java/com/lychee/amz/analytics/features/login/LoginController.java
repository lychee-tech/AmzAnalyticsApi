package com.lychee.amz.analytics.features.login;

import com.lychee.amz.analytics.advice.ISecurityAdvice;
import com.lychee.amz.analytics.features.authentication.help.JwtHelp;
import com.lychee.amz.analytics.features.authentication.model.AuthUser;
import com.lychee.amz.analytics.utl.AuthorityUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@RestController
public class LoginController {
    @Autowired
    private ISecurityAdvice securityAdvice;


    @RequestMapping(value="/login", method= RequestMethod.POST)
    public AuthUserDTO login(HttpServletResponse response){
        AuthUser authUser = securityAdvice.getPrinciple();
        String token = JwtHelp.createJwtToken(authUser);
        response.addHeader("Authorization", "Bearer " + token);
        response.setStatus(HttpStatus.CREATED.value());

        AuthUserDTO dto = new AuthUserDTO();
        dto.setId(authUser.getId());
        dto.setDisplayName(authUser.getDisplayName());
        dto.setRoles(new HashSet<>(AuthorityUtl.getRolesFromAuthorities(authUser.getAuthorities())));
        return dto;
    }
}
