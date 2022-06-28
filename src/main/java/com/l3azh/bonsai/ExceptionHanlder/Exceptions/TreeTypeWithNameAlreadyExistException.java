package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class TreeTypeWithNameAlreadyExistException extends Exception{
    public TreeTypeWithNameAlreadyExistException() {
        super();
    }

    public TreeTypeWithNameAlreadyExistException(String message) {
        super(message);
    }

    public TreeTypeWithNameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeTypeWithNameAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected TreeTypeWithNameAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
