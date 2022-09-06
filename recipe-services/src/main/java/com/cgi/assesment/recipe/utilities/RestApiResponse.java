package com.cgi.assesment.recipe.utilities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Represent Response from API 
 * @author prant
 *
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestApiResponse<T> {
	
	@Schema(required = true, defaultValue = "status",example = "Success")
	private String status;
	
	@Schema(required = true, defaultValue = "message", example = "message for api success.")
	private String message;
	
	@JsonProperty("responseData")
	@Schema(name = "data")
	private T responseData;
	
	private LocalDateTime timeStamp;
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public T getResponseData() {
		return responseData;
	}

	@JsonProperty("responseData")
	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}


	public RestApiResponse(String status, String message, T responseData) {
		this();
		this.status = status;
		this.message = message;
		this.responseData = responseData;
	}
	
	
	public RestApiResponse() {
		this.timeStamp =  LocalDateTime.now();
	}
	
		
	
	

}
