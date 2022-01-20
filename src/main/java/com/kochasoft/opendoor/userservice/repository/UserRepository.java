package com.kochasoft.opendoor.userservice.repository;

import com.kochasoft.opendoor.userservice.domain.*;
import org.springframework.cloud.gcp.data.firestore.*;

import reactor.core.publisher.Mono;

public interface UserRepository extends FirestoreReactiveRepository<User>{
	Mono<User> findByMobileNumber(String mobileNumber);
}
