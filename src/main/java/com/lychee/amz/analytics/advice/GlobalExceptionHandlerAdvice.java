package com.lychee.amz.analytics.advice;

import com.lychee.amz.analytics.Exception.ApiErrorResponse;
import com.lychee.amz.analytics.Exception.ILycheeFriendlyException;
import com.lychee.amz.analytics.Exception.LycheeException;
import com.lychee.amz.analytics.Exception.LycheeRunTimeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @ExceptionHandler(value = { LycheeRunTimeException.class})
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> handleException(LycheeRunTimeException ex) {
        logger.error(ex.getMessage(), ex);

        if (ex instanceof ILycheeFriendlyException) {
            ApiErrorResponse body = new ApiErrorResponse(ex.getCode(), ex.getMessage());
            HttpStatus responseStatus = HttpStatus.valueOf(ex.getStatus());
            return new ResponseEntity<>(body, responseStatus);
        } else {
            ApiErrorResponse body = new ApiErrorResponse("InternalError","Internal Error");
            HttpStatus responseStatus = HttpStatus.valueOf(500);
            return new ResponseEntity<>(body, responseStatus);
        }
    }

    @ExceptionHandler(value = { LycheeException.class})
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> handleException(LycheeException ex) {
        logger.error(ex.getMessage(), ex);

        if (ex instanceof ILycheeFriendlyException) {
            ApiErrorResponse body = new ApiErrorResponse(ex.getCode(), ex.getMessage());
            HttpStatus responseStatus = HttpStatus.valueOf(ex.getStatus());
            return new ResponseEntity<>(body, responseStatus);
        } else {
            ApiErrorResponse body = new ApiErrorResponse("InternalError","Internal Error");
            HttpStatus responseStatus = HttpStatus.valueOf(500);
            return new ResponseEntity<>(body, responseStatus);
        }
    }


    @ExceptionHandler(value = { ConstraintViolationException.class})
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> handleException(ConstraintViolationException ex) {
        logger.error(ex.getMessage(), ex);

        List<String> violations = ConstraintViolationExceptionHelp.getViolationMessages(ex);

        ApiErrorResponse body = new ApiErrorResponse("InvalidInput", StringUtils.join(violations,";"));
        HttpStatus responseStatus = HttpStatus.valueOf(400);
        return new ResponseEntity<>(body, responseStatus);
    }


    @ExceptionHandler(value = { Exception.class})
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        logger.error(ex.getMessage(), ex);

        ApiErrorResponse body = new ApiErrorResponse("InternalError","Internal Error");
        HttpStatus responseStatus = HttpStatus.valueOf(500);
        return new ResponseEntity<>(body, responseStatus);
    }
}
