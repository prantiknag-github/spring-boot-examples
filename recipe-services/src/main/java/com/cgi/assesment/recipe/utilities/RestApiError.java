package com.cgi.assesment.recipe.utilities;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RestApiError {
	private HttpStatus status;
	private String message;
	private LocalDateTime timeStamp;
	
	public RestApiError() {
		this.timeStamp =  LocalDateTime.now();
	}
	
	public RestApiError(HttpStatus status,String message) {
		this();
		this.status= status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	
	
	

}
