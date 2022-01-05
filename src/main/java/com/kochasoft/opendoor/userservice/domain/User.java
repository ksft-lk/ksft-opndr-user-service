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
	public String id;
	public LocalDateTime createdBy;
	public LocalDateTime updatedBy;
	public LocalDateTime createdAt;
	public LocalDateTime updatedAt;
	public List<Action> actions;
	public String uuid;
	public String name;
	public String contactEmail;
	public String country;
	public String email;
	public boolean emailVerified;
	public String mobileCountryCode;
	public String mobileNumber;
	public String avatar;
	
	public static class Action {
		public String action ; 
		public String actionBy;
		public String actionAt;

		public Action(String action,String actionBy,String actionAt) {
			this.action=action;
			this.actionBy=actionBy;
			this.actionAt=actionAt;
		}
	}

}
