package com.kochasoft.opendoor.userservice.repository;

import com.kochasoft.opendoor.userservice.domain.*;
import org.springframework.cloud.gcp.data.firestore.*;

public interface UserRepository extends FirestoreReactiveRepository<User>{
	
}
