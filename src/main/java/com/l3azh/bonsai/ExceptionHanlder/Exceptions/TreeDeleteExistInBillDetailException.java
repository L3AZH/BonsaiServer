package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class TreeDeleteExistInBillDetailException extends Exception{
    public TreeDeleteExistInBillDetailException() {
        super();
    }

    public TreeDeleteExistInBillDetailException(String message) {
        super(message);
    }

    public TreeDeleteExistInBillDetailException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeDeleteExistInBillDetailException(Throwable cause) {
        super(cause);
    }

    protected TreeDeleteExistInBillDetailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
