package com.crs.grocery4j.exception;

/**
 * Created by crs on 9/3/18.
 */
public class CanNotBeUpdatedException extends RuntimeException {

    public CanNotBeUpdatedException() {
    }

    public CanNotBeUpdatedException(String message) {
        super(message);
    }

    public CanNotBeUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotBeUpdatedException(Throwable cause) {
        super(cause);
    }

    public CanNotBeUpdatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
