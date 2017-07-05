package com.lychee.amz.analytics.features.authentication.exception;

import com.lychee.amz.analytics.Exception.ILycheeFriendlyException;
import com.lychee.amz.analytics.advice.ErrorMessageAdvice;
import org.springframework.security.core.AuthenticationException;


public class BadCredentialException extends AuthenticationException implements ILycheeFriendlyException {
    public BadCredentialException() {
        super(ErrorMessageAdvice.getSingleton().badCredential);
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
