package com.lychee.amz.analytics.features.auth.exception;


import com.lychee.amz.analytics.Exception.ILycheeFriendlyException;
import com.lychee.amz.analytics.Exception.LycheeRunTimeException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends LycheeRunTimeException implements ILycheeFriendlyException {
    public UserNotFoundException(String message) {
        super(HttpStatus.UNAUTHORIZED.value(),"AuthError",message);
    }
}
