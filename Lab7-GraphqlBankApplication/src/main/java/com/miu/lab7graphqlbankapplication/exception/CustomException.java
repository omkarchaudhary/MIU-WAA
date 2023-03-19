package com.miu.lab7graphqlbankapplication.exception;

public class CustomException {
    private String errorMessage;

    public CustomException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
