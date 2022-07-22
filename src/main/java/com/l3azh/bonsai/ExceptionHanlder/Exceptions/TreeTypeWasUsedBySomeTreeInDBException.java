package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class TreeTypeWasUsedBySomeTreeInDBException extends  Exception{
    public TreeTypeWasUsedBySomeTreeInDBException() {
        super();
    }

    public TreeTypeWasUsedBySomeTreeInDBException(String message) {
        super(message);
    }

    public TreeTypeWasUsedBySomeTreeInDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeTypeWasUsedBySomeTreeInDBException(Throwable cause) {
        super(cause);
    }

    protected TreeTypeWasUsedBySomeTreeInDBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
