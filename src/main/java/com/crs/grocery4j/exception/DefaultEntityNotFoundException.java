package com.crs.grocery4j.exception;

/**
 * Created by crs on 9/5/18.
 */
public class DefaultEntityNotFoundException extends RuntimeException {

    public DefaultEntityNotFoundException() {
    }

    public DefaultEntityNotFoundException(String message) {
        super(message);
    }

    public DefaultEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultEntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public DefaultEntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
