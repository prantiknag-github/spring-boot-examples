package com.cgi.assesment.recipe.entity;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * Represent for Recipe entity
 * @author prantik
 *
 */
@Document(collection = "collectionRecipes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeEntityBean extends BaseEntityBean<String> {
	@Transient
	public String recipeId;
	
	public String recipeName;
	public String cuisine;
	public Boolean isVegetarian;
	public Integer noOfServings;
	public List<String> ingredients;
	public String instructions;	
	
}
