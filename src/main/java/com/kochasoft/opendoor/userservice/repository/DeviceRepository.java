package com.kochasoft.opendoor.userservice.repository;

import com.kochasoft.opendoor.userservice.domain.Device;
import com.kochasoft.opendoor.userservice.domain.Status;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import reactor.core.publisher.Flux;

public interface DeviceRepository extends FirestoreReactiveRepository<Device> {
    Flux<Device> findByUserIdAndStatus(String userId, Status status);
}
