package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class NoneBillFoundException extends Exception{
    public NoneBillFoundException() {
        super();
    }

    public NoneBillFoundException(String message) {
        super(message);
    }

    public NoneBillFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneBillFoundException(Throwable cause) {
        super(cause);
    }

    protected NoneBillFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
