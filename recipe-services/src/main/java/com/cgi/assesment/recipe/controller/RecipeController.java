package com.cgi.assesment.recipe.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.cgi.assesment.recipe.exceptions.RecipeNotFoundException;
import com.cgi.assesment.recipe.model.RecipeInfoDTO;
import com.cgi.assesment.recipe.model.SearchInfoDTO;
import com.cgi.assesment.recipe.services.RecipeServices;
import com.cgi.assesment.recipe.utilities.RestApiResponse;
import com.cgi.assesment.recipe.utilities.ConstantUtil;
import com.cgi.assesment.recipe.utilities.ResponseUtils;
import com.cgi.assesment.recipe.validator.RecipeValidator;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Represent Rest Controller or APIs for recipes
 * @author prantik
 *
 */
@RestController
@ApiResponses({
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "400",description = "The request is malformed or invalid."),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "401",description = "The authorization token is invalid."),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "403",description = "The user does not have the necessary privileges to perform the operation."),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "422",description = "The resource data can not be processed."),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "500",description = "An internal server error occurred."),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "503",description = "A service is unreachable."),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "504",description = "Gateway Timeout error."),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "201",description = "Recipe information persisted successfully."),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "200",description = "Recipe information retrieve or delete operation successfully.")
})
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
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Object save(@Valid @RequestBody RecipeInfoDTO recipeDTO) {
		ResponseEntity<RestApiResponse> response;
		try {
			recipeServices.save(recipeDTO);
			response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.CREATED, "Recipe information persisted successfully.",recipeDTO);
		} catch(Exception ex) {
			response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.FAILED, "Error in Recipe information creation!!",recipeDTO);
		}
		return response;
	}
	
	/**
	 * Update recipe
	 * @param recipeInfoBean
	 * @return
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object update(@RequestBody RecipeInfoDTO recipeDTO) {
		ResponseEntity<RestApiResponse> response;
		try {
			recipeServices.update(recipeDTO);
			response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.CREATED, "Recipe information updated successfully.",recipeDTO);
		} catch(Exception ex) {
			response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.FAILED, "Error in Recipe information updation!!",recipeDTO);
		}
		return response;
	}
	
	/**
	 * Remove recipe
	 * @param recipeId
	 * @return
	 */
	@DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Object delete(@PathVariable(value = "id") String recipeId) {
		Boolean result = false;
		result = recipeServices.delete(recipeId);
		return ResponseUtils.buildSuccessResponse(ConstantUtil.Status.SUCCESS, "Recipe information deleted successfully.",result);
				
	}
	
	/**
	 *  Search recipes with different search criteria
	 * @param servedFor
	 * @param includeIngredient
	 * @param excludeIngredient
	 * @param instruction
	 * @return list of recipes
	 */
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Object search(@RequestParam(required = false, name="isVeg") Boolean isVeg,
			@RequestParam(required = false, name="servedFor") Integer servedFor,
			@RequestParam(required = false, name="includeIngredients") List<String> includeIngredients,
			@RequestParam(required = false, name="excludeIngredients") List<String> excludeIngredients,
			@RequestParam(required = false, name="instructions") String instruction
			) {
		
		SearchInfoDTO searchDTO = new RecipeValidator().validateSearchCriteria(isVeg, servedFor, 
				includeIngredients, excludeIngredients, instruction);
		 ResponseEntity<RestApiResponse> response;
		 List<?> recipeList = recipeServices.search(searchDTO);
		 if(Optional.ofNullable(recipeList).isPresent() && recipeList.size()>0) {
			 response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.SUCCESS, "List of Recipe information details fetched successfully.",recipeList);
		 } else {
			 throw new RecipeNotFoundException("No Recipe information details found!!");			 
		 }
		 return response;
	}
	
	

}
