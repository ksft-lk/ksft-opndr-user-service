package com.kochasoft.opendoor.userservice.dto;


public class ResponseDTO {
	public static ResponseDTO success() {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus(ResponseStatusCode.SUCCESS);
		return responseModel;
	}

	public static ResponseDTO success(Object result) {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus(ResponseStatusCode.SUCCESS);
		responseModel.setResult(result);
		return responseModel;
	}
	
	public static ResponseDTO failed() {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus(ResponseStatusCode.FAIL);
		return responseModel;
	}

	public static ResponseDTO success(String title, String description) {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus(ResponseStatusCode.SUCCESS);
		responseModel.setStatusCode(ResponseStatusCode.SUCCESS);
		responseModel.setTitle(title);
		responseModel.setDescription(description);
		return responseModel;
	}
	
	public static ResponseDTO failed(ResponseStatusCode statusCode,String title, String description) {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus(ResponseStatusCode.FAIL);
		responseModel.setStatusCode(statusCode);
		responseModel.setTitle(title);
		responseModel.setDescription(description);
		return responseModel;
	}

	public static ResponseDTO sendStatus(ResponseStatusCode statusCode,Object res) {
		ResponseDTO responseModel = new ResponseDTO();
		responseModel.setStatus(statusCode);
		responseModel.setStatusCode(statusCode);
		responseModel.setResult(res);
		return responseModel;
	}

	private ResponseStatusCode status;
	private ResponseStatusCode statusCode;
	private String title;
	private String description;
	private Object result;

	public ResponseStatusCode getStatus() {
		return status;
	}

	public void setStatus(ResponseStatusCode status) {
		this.status = status;
	}

	public ResponseStatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(ResponseStatusCode statusCode) {
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

	public enum ResponseStatusCode{
		SUCCESS,FAIL,CRITICAL,WARNING,INFO,YES,NO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}