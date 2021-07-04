package com.sample.user.core;

public enum ErrorStatus {
     //User
    Err_EXIST("Account Creation failed"),
    Err_NO_EXIST("No User found"),
    Err_NO_UPDATE("User updatation failed");
    private final String errMsg;

    ErrorStatus(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrorMessage() {
        return errMsg;
    }
}
