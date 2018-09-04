package com.crs.grocery4j.domain.dto;

/**
 * Created by crs on 9/3/18.
 */
public class ErrorDto {

    private String message;

    private String cause;

    public ErrorDto(Exception e) {
        this.message = e.getMessage();
        this.cause = e.getCause().toString();
    }

    public ErrorDto(String message) {
        this.message = message;
    }

    public ErrorDto(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
