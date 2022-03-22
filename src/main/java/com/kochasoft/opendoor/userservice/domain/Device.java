package com.kochasoft.opendoor.userservice.domain;

import com.google.cloud.firestore.annotation.DocumentId;
import com.kochasoft.opendoor.userservice.dto.DeviceDTO;
import lombok.ToString;
import org.springframework.cloud.gcp.data.firestore.Document;

@Document(collectionName = "devices")
@ToString
public class Device {

  @DocumentId private String id;

  private String userId;
  private String token;
  private Status status;
  private String deviceId;

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

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public DeviceDTO createDTO() {
    final DeviceDTO dto = new DeviceDTO();
    dto.setId(id);
    dto.setUserId(userId);
    dto.setToken(token);
    dto.setStatus(status);

    return dto;
  }
}
