package com.kochasoft.opendoor.userservice.service;

import com.kochasoft.opendoor.userservice.domain.Device;
import com.kochasoft.opendoor.userservice.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

  @Autowired DeviceRepository deviceRepository;

  @Override
  public Device createDevice(Device device) {
    return deviceRepository.save(device).block();
  }
}
