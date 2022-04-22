package com.kochasoft.opendoor.userservice.dto;

import java.util.List;

import com.kochasoft.opendoor.userservice.domain.Device;

public class UserDTO {
	private String id;
	private String uuid;
	private String name;
	private String contactEmail;
	private String country;
	private String email;
	private String mobileCountryCode;
	private String mobileNumber;
	private String avatar;
	private String redeemCode;
	private List<Device> devices;
	private Boolean canCreateCards;

	
	public String getId() {
		return id;
	}
	public Boolean getCanCreateCards() {
		return canCreateCards;
	}
	public void setCanCreateCards(Boolean canCreateCards) {
		this.canCreateCards = canCreateCards;
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
	public String getRedeemCode() {
		return redeemCode;
	}
	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	} 
	
	
}
