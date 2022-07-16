package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class NoneBillFoundWithEmailException extends Exception{
    public NoneBillFoundWithEmailException() {
        super();
    }

    public NoneBillFoundWithEmailException(String message) {
        super(message);
    }

    public NoneBillFoundWithEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneBillFoundWithEmailException(Throwable cause) {
        super(cause);
    }

    protected NoneBillFoundWithEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
