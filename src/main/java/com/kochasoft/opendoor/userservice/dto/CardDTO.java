package com.kochasoft.opendoor.userservice.dto;

import java.util.List;

import com.kochasoft.opendoor.userservice.domain.Status;
public class CardDTO {
	
	private String id;
	private String userId;
	private int expiration;
	private Local userDisplayName;
	private Local subTitle;
	private String avatar;
	private Local description;
	private int chatLimit;
	private Local subscription;	
	private List<String> exceptionUserList;
	private String restrictionType;
	private Status status;
	private int chatExpiration;
	private boolean workflowOptionsAvailable;

	
	
	public String getUserId() {
		return userId;
	}

	public boolean isWorkflowOptionsAvailable() {
		return workflowOptionsAvailable;
	}

	public void setWorkflowOptionsAvailable(boolean workflowOptionsAvailable) {
		this.workflowOptionsAvailable = workflowOptionsAvailable;
	}

	public String getRestrictionType() {
		return restrictionType;
	}

	public void setRestrictionType(String restrictionType) {
		this.restrictionType = restrictionType;
	}

	public int getChatExpiration() {
		return chatExpiration;
	}

	public void setChatExpiration(int chatExpiration) {
		this.chatExpiration = chatExpiration;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Local getSubscription() {
		return subscription;
	}

	public void setSubscription(Local subscription) {
		this.subscription = subscription;
	}

	public int getExpiration() {
		return expiration;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	public Local getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(Local userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public void setDescription(Local description) {
		this.description = description;
	}

	
	public Local getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(Local subTitle) {
		this.subTitle = subTitle;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getChatLimit() {
		return chatLimit;
	}

	public void setChatLimit(int chatLimit) {
		this.chatLimit = chatLimit;
	}

	public List<String> getExceptionUserList() {
		return exceptionUserList;
	}

	public void setExceptionUserList(List<String> exceptionUserList) {
		this.exceptionUserList = exceptionUserList;
	}

	public Local getDescription() {
		return description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	

}
