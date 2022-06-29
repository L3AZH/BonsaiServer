package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class NoneTreeFoundWithUUIDException extends Exception {
    public NoneTreeFoundWithUUIDException() {
        super();
    }

    public NoneTreeFoundWithUUIDException(String message) {
        super(message);
    }

    public NoneTreeFoundWithUUIDException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneTreeFoundWithUUIDException(Throwable cause) {
        super(cause);
    }

    protected NoneTreeFoundWithUUIDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
