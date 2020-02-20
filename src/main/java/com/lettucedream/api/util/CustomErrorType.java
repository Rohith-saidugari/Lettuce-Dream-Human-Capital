package com.lettucedream.api.util;


import org.springframework.stereotype.Component;

@Component
public class CustomErrorType {

    private String errorMessage;

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
