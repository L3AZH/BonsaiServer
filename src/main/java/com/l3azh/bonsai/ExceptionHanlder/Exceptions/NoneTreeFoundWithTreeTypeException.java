package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class NoneTreeFoundWithTreeTypeException extends Exception{
    public NoneTreeFoundWithTreeTypeException() {
        super();
    }

    public NoneTreeFoundWithTreeTypeException(String message) {
        super(message);
    }

    public NoneTreeFoundWithTreeTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneTreeFoundWithTreeTypeException(Throwable cause) {
        super(cause);
    }

    protected NoneTreeFoundWithTreeTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
