package com.kochasoft.opendoor.userservice.service;

import com.kochasoft.opendoor.userservice.domain.Device;
import com.kochasoft.opendoor.userservice.domain.Status;

import java.util.List;

public interface DeviceService {

  public Device createDevice(Device device);

  public List<Device> findByUserIdAndStatus(String userId, Status status);
}
