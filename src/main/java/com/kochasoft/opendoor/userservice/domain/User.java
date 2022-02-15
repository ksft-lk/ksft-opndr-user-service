package com.kochasoft.opendoor.userservice.domain;

import java.util.List;

import com.google.cloud.firestore.annotation.DocumentId;

import org.springframework.cloud.gcp.data.firestore.Document;

import lombok.ToString;

@Document(collectionName = "users")
@ToString
public class User {
	
	@DocumentId
	private String id;
	private String createdBy;
	private String updatedBy;
	private long createdAt;
	private long updatedAt;
	private List<Action> actions;
	private String uuid;
	private String name;
	private String contactEmail;
	private String country;
	private String email;
	private boolean emailVerified;
	private String mobileCountryCode;
	private String mobileNumber;
	private String avatar;
	private Status status;
	private List<Device> devices;
	public static class Device{

		private String token;
		private Status status;
		private String deviceId;
		public Status getStatus() {
			return status;
		}
		public String getDeviceId() {
			return deviceId;
		}
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public void setStatus(Status status) {
			this.status = status;
		}
	}
	public static class Action {
		private String action ; 
		private String actionBy;
		private long actionAt;

		public Action(String action,String actionBy,long actionAt) {
			this.action=action;
			this.actionBy=actionBy;
			this.actionAt=actionAt;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getActionBy() {
			return actionBy;
		}

		public void setActionBy(String actionBy) {
			this.actionBy = actionBy;
		}

		public long getActionAt() {
			return actionAt;
		}

		public void setActionAt(long actionAt) {
			this.actionAt = actionAt;
		}
		
		
	}

	public String getId() {
		return id;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getMobileCountryCode() {
		return mobileCountryCode;
	}

	public void setMobileCountryCode(String mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	

}
