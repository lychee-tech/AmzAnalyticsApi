package com.lychee.amz.analytics.Exception;

/**
 * base for checked exception
 *
 */
public class LycheeException extends Exception {
    int Status;
    private String code;


    public LycheeException(int status, String code, String message) {
        super(message);
        this.Status = status;
        this.code = code;
    }


    public String getCode() {
        return code;
    }


    public LycheeException setCode(String code) {
        this.code = code;
        return this;
    }


    public int getStatus() {
        return Status;
    }


    public LycheeException setStatus(int status) {
        Status = status;
        return this;
    }
}
