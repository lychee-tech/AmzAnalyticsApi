package com.lychee.amz.analytics.Exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lychee.amz.analytics.advice.ErrorMessageAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApiErrorHelp {
    private static Logger logger = LoggerFactory.getLogger(ApiErrorHelp.class);

    public static  String toJson(ApiErrorResponse apiErrorResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(apiErrorResponse);
        } catch (Exception ex) {
            logger.error("failed to serialize exception json", ex);
            return ErrorMessageAdvice.staticInternalError;
        }
    }
}
