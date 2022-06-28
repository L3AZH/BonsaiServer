package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class NoneTreeTypeFoundException extends Exception{
    public NoneTreeTypeFoundException() {
        super();
    }

    public NoneTreeTypeFoundException(String message) {
        super(message);
    }

    public NoneTreeTypeFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneTreeTypeFoundException(Throwable cause) {
        super(cause);
    }

    protected NoneTreeTypeFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
