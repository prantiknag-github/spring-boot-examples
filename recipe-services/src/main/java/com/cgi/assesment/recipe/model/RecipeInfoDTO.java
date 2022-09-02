package com.cgi.assesment.recipe.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * Represent a Recipe request bean
 * @author prantik
 *
 */

@Getter
@Setter
@ToString
public class RecipeInfoDTO {
	public String recipeIdReq;
	public String recipeNameReq;
	public String cuisineReq;
	public Boolean isVegetarianReq;
	public Integer noOfServingsReq;
	public List<String> ingredientsReq;
	public String instructionsReq;
}
