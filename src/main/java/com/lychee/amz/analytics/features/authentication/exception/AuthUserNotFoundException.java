package com.lychee.amz.analytics.features.authentication.exception;


import com.lychee.amz.analytics.Exception.ILycheeFriendlyException;
import com.lychee.amz.analytics.advice.CodeAdvice;
import com.lychee.amz.analytics.advice.MessageAdvice;
import org.springframework.security.core.AuthenticationException;

public class AuthUserNotFoundException extends AuthenticationException implements ILycheeFriendlyException {
    public AuthUserNotFoundException() {
        super(MessageAdvice.getSingleton().authUserNotFound);
    }

    @Override
    public int getStatus() {
        return 401;
    }

    @Override
    public String getCode() {
        return CodeAdvice.singleton.authenticationError;
    }
}
