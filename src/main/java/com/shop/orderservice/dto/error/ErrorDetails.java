package com.shop.orderservice.dto.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetails 
{
	private String message;
	private String title;
	private long timestamp;
	private int status;
	private String developerMsg;
	
	private Map<String, List<ValidationError>> errors = new HashMap<>(); 
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDeveloperMsg() {
		return developerMsg;
	}
	public void setDeveloperMsg(String developerMsg) {
		this.developerMsg = developerMsg;
	}
	public Map<String, List<ValidationError>> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, List<ValidationError>> errors) {
		this.errors = errors;
	}
	
	

}
