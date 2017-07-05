package com.lychee.amz.analytics.features.auth.exception;

import com.lychee.amz.analytics.Exception.ILycheeFriendlyException;
import com.lychee.amz.analytics.Exception.LycheeRunTimeException;
import com.lychee.amz.analytics.advice.ErrorMessageAdvice;
import org.springframework.http.HttpStatus;


public class BadCredentialException extends LycheeRunTimeException implements ILycheeFriendlyException {
    public BadCredentialException(){
        super(HttpStatus.UNAUTHORIZED.value(), "AuthenticationError", ErrorMessageAdvice.getSingleton().badCredential);
    }
}
