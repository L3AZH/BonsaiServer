package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class TreeWithNameAlreadyExistException extends Exception{
    public TreeWithNameAlreadyExistException() {
        super();
    }

    public TreeWithNameAlreadyExistException(String message) {
        super(message);
    }

    public TreeWithNameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeWithNameAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected TreeWithNameAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
