package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class AccountWithEmailNotFoundException extends Exception{

    public AccountWithEmailNotFoundException() {
        super();
    }

    public AccountWithEmailNotFoundException(String message) {
        super(message);
    }

    public AccountWithEmailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountWithEmailNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AccountWithEmailNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
