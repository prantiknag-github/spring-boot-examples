package com.cgi.assesment.recipe.exceptions;
/**
 * Custom Exception for Recipe not found
 * @author prantik
 *
 */
public class RecipeNotFoundException extends RuntimeException {

	public RecipeNotFoundException(String message) {
		super(message);
	}
}
