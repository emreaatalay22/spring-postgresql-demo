package com.emretest.exception;



public class BaseException extends RuntimeException {

    public BaseException() {

    }
    public BaseException(ErrorMessage message) {
        super(message.prepareErrorMessage());
    }
}

