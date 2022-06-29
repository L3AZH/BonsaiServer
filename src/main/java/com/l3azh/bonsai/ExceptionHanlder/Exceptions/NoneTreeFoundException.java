package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class NoneTreeFoundException extends Exception{
    public NoneTreeFoundException() {
        super();
    }

    public NoneTreeFoundException(String message) {
        super(message);
    }

    public NoneTreeFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneTreeFoundException(Throwable cause) {
        super(cause);
    }

    protected NoneTreeFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
