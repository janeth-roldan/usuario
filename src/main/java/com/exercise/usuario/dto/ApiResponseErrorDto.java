package com.exercise.usuario.dto;

public class ApiResponseErrorDto {

    private String message;

    public ApiResponseErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
