package com.cgi.assesment.recipe.exceptions.handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cgi.assesment.recipe.utilities.RestApiError;
import com.cgi.assesment.recipe.utilities.ResponseUtils;
/**
 * Represent to handle common exception
 * @author prantik
 *
 */
@Order(Integer.MIN_VALUE)
@ControllerAdvice
public class BaseExceptionHandler {

	public BaseExceptionHandler() {
		// TODO Auto-generated constructor stub
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<RestApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result= ex.getBindingResult();
		RestApiError apiError = new RestApiError(HttpStatus.BAD_REQUEST,"No. of Validation errors: "+result.getFieldErrorCount());
		return ResponseUtils.buildErrorResponse(apiError);
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<RestApiError> handleGenericException(Exception ex) {
		RestApiError apiError= new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return  ResponseUtils.buildErrorResponse(apiError);
	}
}
