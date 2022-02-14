package com.kochasoft.opendoor.userservice.dto;

public class TokenDTO{
    private String token;
    private boolean login;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public boolean getLogin() {
        return login;
    }
    public void setLogin(boolean login) {
        this.login = login;
    }

    
}