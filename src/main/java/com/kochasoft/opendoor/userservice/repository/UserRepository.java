package com.kochasoft.opendoor.userservice.repository;

import com.kochasoft.opendoor.userservice.domain.*;
import org.springframework.cloud.gcp.data.firestore.*;

import reactor.core.publisher.Mono;

public interface UserRepository extends FirestoreReactiveRepository<User>{
	Mono<User> findByMobileNumberAndMobileCountryCode(String mobileNumber,String mobileCountryCode);
	Mono<User> findByUuidAndStatus(String uuid,Status status);
}
