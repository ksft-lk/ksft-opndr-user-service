package com.kochasoft.opendoor.userservice.dto;

public class ResponseDTO {
	public static ResponseDTO SUCCESS() {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus("SUCCESS");
		return responseModel;
	}

	
	public static ResponseDTO SUCCESS(Object res) {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus("SUCCESS");
		responseModel.setResult(res);
		return responseModel;
	}

	public static ResponseDTO sendStatus(String status,String description, Object result) {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus(status);
		responseModel.setDescription(description);
		responseModel.setResult(result);
		return responseModel;
	}
	
	public static ResponseDTO FAILED() {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus("FAILED");
		return responseModel;
	}
	
	public static ResponseDTO FAILED(int statusCode,String description) {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus("FAILED");
		responseModel.setStatusCode(statusCode);
		responseModel.setDescription(description);
		return responseModel;
	}
	private String status;
	private int statusCode;
	private String description;
	private Object result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	
	
	
}
