package com.lychee.amz.analytics.features.login;

import com.lychee.amz.analytics.advice.ISecurityAdvice;
import com.lychee.amz.analytics.features.authentication.help.JwtHelp;
import com.lychee.amz.analytics.features.authentication.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    private ISecurityAdvice securityAdvice;


    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseEntity<?> login(HttpServletResponse response){
        AuthUser authUser = securityAdvice.getPrinciple();
        String token = JwtHelp.createJwtToken(authUser);
        return ResponseEntity.noContent().header("Authorization", "Bearer " + token).build();
    }
}
