package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class NoneBillFoundWithUUIDException extends Exception{
    public NoneBillFoundWithUUIDException() {
        super();
    }

    public NoneBillFoundWithUUIDException(String message) {
        super(message);
    }

    public NoneBillFoundWithUUIDException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneBillFoundWithUUIDException(Throwable cause) {
        super(cause);
    }

    protected NoneBillFoundWithUUIDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
