package com.jinternals.restframework.exception;

public class RestFrameworkException extends RuntimeException {
    public RestFrameworkException() {
    }

    public RestFrameworkException(String message) {
        super(message);
    }

    public RestFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestFrameworkException(Throwable cause) {
        super(cause);
    }

    public RestFrameworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
