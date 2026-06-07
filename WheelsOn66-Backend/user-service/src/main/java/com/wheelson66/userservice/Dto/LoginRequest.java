package com.wheelson66.userservice.Dto;

public class LoginRequest {

    private String email;
    private String rawPassword;

    public String getEmail() {
        return email;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}