package com.lychee.amz.analytics.Exception;

/**
 * use for return error to api user
 */
public class ApiErrorResponse {
    private String code;
    private String message;

    public ApiErrorResponse(){
    }

    public ApiErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }


    public ApiErrorResponse setCode(String code) {
        this.code = code;
        return this;
    }


    public String getMessage() {
        return message;
    }


    public ApiErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
