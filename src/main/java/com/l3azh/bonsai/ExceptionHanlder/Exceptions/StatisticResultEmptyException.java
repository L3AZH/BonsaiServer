package com.l3azh.bonsai.ExceptionHanlder.Exceptions;

public class StatisticResultEmptyException extends Exception{
    public StatisticResultEmptyException() {
        super();
    }

    public StatisticResultEmptyException(String message) {
        super(message);
    }

    public StatisticResultEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatisticResultEmptyException(Throwable cause) {
        super(cause);
    }

    protected StatisticResultEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
