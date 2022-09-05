package com.cgi.assesment.recipe.exceptions.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cgi.assesment.recipe.exceptions.RecipeNotFoundException;
import com.cgi.assesment.recipe.utilities.RestApiError;
import com.cgi.assesment.recipe.utilities.ResponseUtils;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler {
	
	@ExceptionHandler(RecipeNotFoundException.class)
	public ResponseEntity<RestApiError> handleRecipeNotFoundException(RecipeNotFoundException ex) {
		RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return  ResponseUtils.buildErrorResponse(apiError);
	}

}
