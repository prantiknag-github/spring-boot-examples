package com.cgi.assesment.recipe.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * Represent builiding of API response
 * @author prant
 *
 */
public class ResponseUtils {
	
	public ResponseUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static ResponseEntity<RestApiResponse> buildSuccessResponse(String status, String message,Object object) {
		ResponseEntity<RestApiResponse> response;
		if(status.equalsIgnoreCase(ConstantUtil.Status.CREATED)) {
			response = ResponseEntity.status(HttpStatus.CREATED).body(new RestApiResponse(status, message, object));
		} else if(status.equalsIgnoreCase(ConstantUtil.Status.SUCCESS)) {
			response = ResponseEntity.ok(new RestApiResponse(status, message, object));
		} else {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestApiResponse(status, message, object));
		}
		return response;
	}
	
	public static ResponseEntity<RestApiError> buildErrorResponse(RestApiError apiError) {
		return new ResponseEntity(apiError,apiError.getStatus());
	}

}
