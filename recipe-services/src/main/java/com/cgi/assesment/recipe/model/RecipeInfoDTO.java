package com.cgi.assesment.recipe.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@Schema(required = false,name="recipe_id", example="abcd-1234", description = "${recipe.recipeDTO.recipeId}")
	public String recipeIdReq;
	
	@JsonProperty("recipe_name")
	@NotEmpty(message = "${reciepe.recipeDTO.message.recipeName}")
	@Schema(required = true,name = "recipe_name",example="Recipe Example", description = "${recipe.recipeDTO.recipeName}")
	public String recipeNameReq;
	
	@JsonProperty("cuisine")
	@NotEmpty(message = "${reciepe.recipeDTO.message.cuisine}")
	@Schema(required = true,name = "cuisine",example="Cuisine Example",description = "${recipe.recipeDTO.cuisine}")
	public String cuisineReq;
	
	@JsonProperty("is_vegetarian")
	@NotNull(message = "${reciepe.recipeDTO.message.isVegetarian}")
	@Schema(required = true,name = "is_vegetarian",example="true",description = "${recipe.recipeDTO.isVegetarian}")
	public Boolean isVegetarianReq;
	
	@JsonProperty("no_of_servings")
	@NotNull(message = "${reciepe.recipeDTO.message.noOfServings}")
	@Schema(required = true,name= "no_of_servings",example="4",description = "${recipe.recipeDTO.noOfServings}")
	public Integer noOfServingsReq;
	
	@JsonProperty("ingredients")
	@NotEmpty(message = "${reciepe.recipeDTO.message.ingredients}")
	@Schema(required = true,name = "ingredients",example="[\"potatoes\",\"onions\"]",description = "${recipe.recipeDTO.ingredients}")
	public List<String> ingredientsReq;
	
	@JsonProperty("instructions")
	@NotEmpty(message = "${reciepe.recipeDTO.message.instructions}")
	@Schema(required = true,name = "instructions",example="Instruction details.",description = "${recipe.recipeDTO.instructions}")
	public String instructionsReq;
}
