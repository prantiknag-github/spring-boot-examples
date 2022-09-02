package com.cgi.assesment.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.assesment.recipe.model.RecipeInfoDTO;
import com.cgi.assesment.recipe.model.SearchInfoDTO;
import com.cgi.assesment.recipe.services.RecipeServices;
import com.cgi.assesment.recipe.validator.RecipeValidator;

/**
 * Represent Rest Controller or APIs for recipes
 * @author prantik
 *
 */
@RestController
@RequestMapping("recipes")
@CrossOrigin(origins = "*" , allowCredentials = "false")
public class RecipeController {
	
	@Autowired
	RecipeServices recipeServices;
	/**
	 * Add recipe
	 * @param recipeInfoBean
	 * @return 
	 */
	@PostMapping(value="/",consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value = HttpStatus.CREATED)
	public Object save(@RequestBody RecipeInfoDTO recipeDTO) {
		return recipeServices.save(recipeDTO);
	}
	
	/**
	 * Update recipe
	 * @param recipeInfoBean
	 * @return
	 */
	@PutMapping(value="/",consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value = HttpStatus.CREATED)
	public Object update(@RequestBody RecipeInfoDTO recipeDTO) {
		return recipeServices.update(recipeDTO);
	}
	
	/**
	 * Remove recipe
	 * @param recipeId
	 * @return
	 */
	@DeleteMapping(value="/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value = HttpStatus.OK)
	public Object delete(@PathVariable(value = "id") String recipeId) {
		if(recipeServices.delete(recipeId)) {
			return "recipe deleted successfully.";
		} else {
			return "recipe not deleted!!";
		}
	}
	
	/**
	 *  Search recipes with different search criteria
	 * @param servedFor
	 * @param includeIngredient
	 * @param excludeIngredient
	 * @param instruction
	 * @return list of recipes
	 */
	@GetMapping(value="/",consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value = HttpStatus.OK)
	public List<?> search(@RequestParam(required = false, name="isVeg") Boolean isVeg,
			@RequestParam(required = false, name="servedFor") Integer servedFor,
			@RequestParam(required = false, name="includeIngredients") List<String> includeIngredients,
			@RequestParam(required = false, name="excludeIngredients") List<String> excludeIngredients,
			@RequestParam(required = false, name="instructions") String instruction
			) {
		
		SearchInfoDTO searchDTO = new RecipeValidator().validateSearchCriteria(isVeg, servedFor, 
				includeIngredients, excludeIngredients, instruction);
		return recipeServices.search(searchDTO);
	}
	
	

}
