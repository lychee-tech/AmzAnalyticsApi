package com.lychee.amz.analytics.advice;

import com.lychee.amz.analytics.features.authentication.model.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityAdvice implements ISecurityAdvice{
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public AuthUser getPrinciple() {
        Authentication authentication = getAuthentication();
        return (AuthUser) authentication.getPrincipal();
    }
}
