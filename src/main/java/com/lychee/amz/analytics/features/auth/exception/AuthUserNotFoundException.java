package com.lychee.amz.analytics.features.auth.exception;


import com.lychee.amz.analytics.Exception.ILycheeFriendlyException;
import com.lychee.amz.analytics.advice.ErrorMessageAdvice;
import org.springframework.security.core.AuthenticationException;

public class AuthUserNotFoundException extends AuthenticationException implements ILycheeFriendlyException {
    public AuthUserNotFoundException() {
        super(ErrorMessageAdvice.getSingleton().authUserNotFound);
    }

    @Override
    public int getStatus() {
        return 401;
    }

    @Override
    public String getCode() {
        return "AuthenticationError";
    }
}
