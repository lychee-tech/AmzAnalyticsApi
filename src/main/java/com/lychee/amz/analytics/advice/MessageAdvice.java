package com.lychee.amz.analytics.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageAdvice {
    @Value("${error.authentication.user_not_found}")
    public String  authUserNotFound;

    @Value("${error.internal.error}")
    public String internalError;

    @Value("${error.authentication.bad_credential}")
    public String badCredential;


    @Value("${error.validation.blank_email}")
    public String blankEmail;
    @Value("${error.validation.duplicate_email}")
    public String duplicateEmail;
    @Value("${error.validation.email_too_long}")
    public String emailTooLong;
    @Value("${error.validation.bad_email}")
    public String badEmail;

    @Value("{${error.validation.blank_password}")
    public String blankPassword;




    private static MessageAdvice singleton;

    public MessageAdvice() {
        singleton = this;
    }

    public static MessageAdvice getSingleton(){
        return singleton;
    }


}
