package com.lychee.amz.analytics.features.hello;


import com.lychee.amz.analytics.advice.ISecurityAdvice;
import com.lychee.amz.analytics.features.authentication.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private ISecurityAdvice securityAdvice;

    @RequestMapping(value="/api/hello", method= RequestMethod.GET)
    public String getGreeting(){
        Authentication authentication = securityAdvice.getAuthentication();
        AuthUser authUser = securityAdvice.getPrinciple();
        return "success";
    }
}
