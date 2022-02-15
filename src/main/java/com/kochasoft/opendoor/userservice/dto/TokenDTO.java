package com.kochasoft.opendoor.userservice.dto;

public class TokenDTO{
    private String token;
    private boolean login;
    private String deviceId;
    public String getToken() {
        return token;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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