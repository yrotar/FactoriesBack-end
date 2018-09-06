package com.evgen.rest.wrappers;

public class ErrorWrapper {

    private String message;
    private String className;

    private ErrorWrapper(Exception exception) {
        message = exception.getMessage();
        className = exception.getClass().getSimpleName();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public static ErrorWrapper wrap(Exception ex) {
        return new ErrorWrapper(ex);
    }

}
