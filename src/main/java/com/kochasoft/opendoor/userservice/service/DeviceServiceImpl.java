package com.kochasoft.opendoor.userservice.service;

import com.kochasoft.opendoor.userservice.domain.Device;
import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

  @Autowired DeviceRepository deviceRepository;

  @Override
  public Device createDevice(Device device) {
    return deviceRepository.save(device).block();
  }

  @Override
  public List<Device> findByUserIdAndStatus(String userId, Status status) {
    return deviceRepository.findByUserIdAndStatus(userId, status).collectList().block();
  }
}
