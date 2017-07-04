package com.lychee.amz.analytics.Exception;

/**
 * base for run time exception
 *
 */
public class LycheeRunTimeException extends RuntimeException {
    int Status;
    private String code;


    public LycheeRunTimeException(int status, String code, String message) {
        super(message);
        this.Status = status;
        this.code = code;
    }


    public String getCode() {
        return code;
    }


    public LycheeRunTimeException setCode(String code) {
        this.code = code;
        return this;
    }


    public int getStatus() {
        return Status;
    }


    public LycheeRunTimeException setStatus(int status) {
        Status = status;
        return this;
    }
}
