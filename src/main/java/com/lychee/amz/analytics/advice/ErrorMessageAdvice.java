package com.lychee.amz.analytics.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessageAdvice {
    @Value("${error.authentication.user_not_found}")
    public String  authUserNotFound;

    @Value("${error.internal.error}")
    public String internalError;

    public static  String staticInternalError = "Internal error";
}
