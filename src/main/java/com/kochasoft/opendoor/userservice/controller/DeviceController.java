package com.kochasoft.opendoor.userservice.controller;

import com.kochasoft.opendoor.userservice.domain.Device;
import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.dto.DeviceDTO;
import com.kochasoft.opendoor.userservice.dto.ResponseDTO;
import com.kochasoft.opendoor.userservice.dto.TokenDTO;
import com.kochasoft.opendoor.userservice.dto.UserDTO;
import com.kochasoft.opendoor.userservice.service.DeviceService;
import com.kochasoft.opendoor.userservice.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class DeviceController {

  private static final String ERR_EXCP = "please see log for further.";

  @Autowired DeviceService deviceService;

  @PutMapping("/devices")
  public ResponseEntity<ResponseDTO> updateDeviceToken(
      @RequestAttribute("user") UserDTO user, @RequestBody TokenDTO tokenDTO) {
    try {
      final Device device = new Device();
      device.setId(tokenDTO.getDeviceId());
      device.setUserId(user.getId());
      device.setStatus(tokenDTO.getLogin() ? Status.ACTIVE : Status.LOGOUT);
      device.setToken(tokenDTO.getToken());
      device.setDeviceId(tokenDTO.getDeviceId());
      if(tokenDTO.getDeviceId()==null){
        device.setCreatedAt(Util.getCurrentTime());
        device.setUpdatedAt(Util.getCurrentTime());
      }else{
        device.setUpdatedAt(Util.getCurrentTime());
      }

      deviceService.createDevice(device);

      return ResponseEntity.ok().body(ResponseDTO.success());
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(ResponseDTO.failed(ResponseDTO.ResponseStatusCode.FAIL, null, ERR_EXCP));
    }
  }

  @GetMapping("/devices")
  public ResponseEntity<ResponseDTO> findByUserIdAndStatus(
      @RequestParam(value = "userId", required = true) String userId,
      @RequestParam(required = false, defaultValue = "ACTIVE") Status status) {
    try {
      final List<DeviceDTO> devices =
          deviceService.findByUserIdAndStatus(userId, status).stream()
              .map(Device::createDTO)
              .collect(Collectors.toList());

      return ResponseEntity.ok().body(ResponseDTO.success(devices));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(ResponseDTO.failed(ResponseDTO.ResponseStatusCode.FAIL, null, ERR_EXCP));
    }
  }
}