package com.evgen.exception;

public class OperationFailedException extends RuntimeException {
    public OperationFailedException(String msg) {
        super(msg);
    }
}
