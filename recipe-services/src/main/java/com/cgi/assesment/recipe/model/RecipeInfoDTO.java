package com.cgi.assesment.recipe.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
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
	
	@JsonProperty("recipe_id")
	@Schema(required = false,name="recipe_id", example="abcd1234", description = "Recipe Id")
	public String recipeIdReq;
	
	@JsonProperty("recipe_name")
	@NotBlank(message = "Recipe name is missing.")
	@Schema(required = true,name = "recipe_name",example="Recipe Example", description = "Recipe name")
	public String recipeNameReq;
	
	@JsonProperty("cuisine")
	@NotBlank(message = "Cuisine value is missing.")
	@Schema(required = true,name = "cuisine",example="Cuisine Example",description = "Cusine")
	public String cuisineReq;
	
	@JsonProperty("is_vegetarian")
	@NotBlank(message = "Is Vegetarian value is missing.")
	@Schema(required = true,name = "is_vegetarian",example="true",description = "Is Vegetarian")
	public Boolean isVegetarianReq;
	
	@JsonProperty("no_of_servings")
	@NotBlank(message = "No. of Servings value is missing.")
	@Schema(required = true,name= "no_of_servings",example="4",description = "No. of Servings")
	public Integer noOfServingsReq;
	
	@JsonProperty("ingredients")
	@NotBlank(message = "Ingredient values are missing.")
	@Schema(required = true,name = "ingredients",example="[\"potatoes\",\"onions\"]",description = "Ingredients")
	public List<String> ingredientsReq;
	
	@JsonProperty("instructions")
	@NotBlank(message = "Instructions value is missing.")
	@Schema(required = true,name = "instructions",example="Instruction details.",description = "Instructions")
	public String instructionsReq;
}
