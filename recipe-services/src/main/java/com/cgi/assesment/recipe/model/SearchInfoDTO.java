package com.cgi.assesment.recipe.model;

import java.util.List;

import lombok.Getter;

import lombok.Setter;
/**
 * Represent search criteria
 * @author prantik
 *
 */
@Getter
@Setter
public class SearchInfoDTO {
	public Boolean isVegetarian;
	public Integer noOfServings;
	public List<String> includeIngredients;
	public List<String> excludeIngredients;
	public String instructions;	

}
