package com.kochasoft.opendoor.userservice.repository;

import com.kochasoft.opendoor.userservice.domain.Device;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

public interface DeviceRepository extends FirestoreReactiveRepository<Device> {}
