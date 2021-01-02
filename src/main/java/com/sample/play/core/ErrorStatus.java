package com.sample.play.core;

public enum ErrorStatus {
    Err_CREATE("Score does not save"),
    Err_DELETE("Score does not delete"),
    Err_DATA("Data not found");
    private final String errMsg;

    ErrorStatus(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrorMessage() {
        return errMsg;
    }
}
