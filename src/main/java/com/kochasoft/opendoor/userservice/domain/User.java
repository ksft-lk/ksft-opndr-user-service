package com.kochasoft.opendoor.userservice.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

import lombok.Data;
import lombok.ToString;

@Document(collectionName = "users")
@Data
@ToString
public class User {
	@DocumentId
	String id;
	LocalDateTime createdBy;
	LocalDateTime updatedBy;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	List<Action> actions;
	String uuid;
	String name;
	String contactEmail;
	String country;
	String email;
	boolean emailVerified;
	String mobileCountryCode;
	String mobileNumber;
	String avatar;
	
	public static class Action {
		String action ; 
		String actionBy;
		String actionAt;

		public Action(String action,String actionBy,String actionAt) {
			this.action=action;
			this.actionBy=actionBy;
			this.actionAt=actionAt;
		}
	}

}
