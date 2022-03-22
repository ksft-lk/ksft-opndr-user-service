package com.kochasoft.opendoor.userservice.dto;

import com.kochasoft.opendoor.userservice.domain.Status;

public class DeviceDTO {

  private String id;
  private String userId;
  private String token;
  private Status status;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
