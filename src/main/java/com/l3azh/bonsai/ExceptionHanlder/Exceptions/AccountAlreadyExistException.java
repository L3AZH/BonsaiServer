package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class AccountAlreadyExistException extends Exception {

    public AccountAlreadyExistException() {
        super();
    }

    public AccountAlreadyExistException(String message) {
        super(message);
    }

    public AccountAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected AccountAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
