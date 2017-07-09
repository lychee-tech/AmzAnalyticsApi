package com.lychee.amz.analytics.advice;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CodeAdvice {
    @Value("${invalidInput}")
    public String invalidInput;

    @Value("${authenticationError}")
    public String authenticationError;


    public static CodeAdvice singleton;

    public CodeAdvice(){
        singleton = this;
    }

}
