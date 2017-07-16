package com.lychee.amz.analytics.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationAdvice {
    @Value("${jwt.expiration.in.minutes}")
    public Integer jwtExpirationInMinutes;
    @Value(("${jwt.signing.secrete}"))
    public String jwtSigningSecret;

    public static ConfigurationAdvice singleton;
    private ConfigurationAdvice(){
        singleton = this;
    }
}
