package com.crs.grocery4j.exception;

/**
 * Created by crs on 9/3/18.
 */
public class CanNotBeCreatedException extends RuntimeException {

    public CanNotBeCreatedException() {
    }

    public CanNotBeCreatedException(String message) {
        super(message);
    }

    public CanNotBeCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotBeCreatedException(Throwable cause) {
        super(cause);
    }

    public CanNotBeCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
