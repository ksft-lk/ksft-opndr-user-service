package com.kochasoft.opendoor.userservice.model;

public class ResponseModel {
	public static ResponseModel SUCCESS() {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setStatus("SUCCESS");
		return responseModel;
	}
	
	public static ResponseModel FAILED() {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setStatus("FAILED");
		return responseModel;
	}
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
