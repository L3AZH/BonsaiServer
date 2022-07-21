package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class NoneTreeFoundWithNameException extends Exception{
    public NoneTreeFoundWithNameException() {
        super();
    }

    public NoneTreeFoundWithNameException(String message) {
        super(message);
    }

    public NoneTreeFoundWithNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneTreeFoundWithNameException(Throwable cause) {
        super(cause);
    }

    protected NoneTreeFoundWithNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
