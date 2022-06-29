package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class NoneTreeTypeFoundWithUUIDException extends Exception{
    public NoneTreeTypeFoundWithUUIDException() {
        super();
    }

    public NoneTreeTypeFoundWithUUIDException(String message) {
        super(message);
    }

    public NoneTreeTypeFoundWithUUIDException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneTreeTypeFoundWithUUIDException(Throwable cause) {
        super(cause);
    }

    protected NoneTreeTypeFoundWithUUIDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
