package com.cgi.assesment.recipe.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
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
import com.cgi.assesment.recipe.services.impl.RecipeServicesImpl;
import com.cgi.assesment.recipe.utilities.RestApiResponse;
import com.cgi.assesment.recipe.utilities.ConstantUtil;
import com.cgi.assesment.recipe.utilities.PropertiesConfig;
import com.cgi.assesment.recipe.utilities.ResponseUtils;
import com.cgi.assesment.recipe.validator.RecipeValidator;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;
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
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "400",description = "${recipe.responseCode.400}"),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "401",description = "${recipe.responseCode.401}"),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "403",description = "${recipe.responseCode.403}"),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "422",description = "${recipe.responseCode.422}"),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "500",description = "${recipe.responseCode.500}"),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "503",description = "${recipe.responseCode.503}"),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "504",description = "${recipe.responseCode.504}"),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "201",description = "${recipe.responseCode.201}"),
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema= @Schema(implementation = RestApiResponse.class)),responseCode = "200",description = "${recipe.responseCode.200}")
})
@RequestMapping("recipes")
@CrossOrigin(origins = "*" , allowCredentials = "false")
public class RecipeController {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(RecipeController.class);
	
	@Autowired
	RecipeServices recipeServices;
	/**
	 * Add recipe
	 * @param recipeInfoBean
	 * @return ResponseEntity<RestApiResponse>
	 */
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	@Operation(description = "${recipe.api.desc.save}", summary ="${recipe.api.summary.save}")
	public Object save(@Valid @RequestBody RecipeInfoDTO recipeDTO) {
		logger.info("invoke save()");
		logger.debug("invoke save() payload:",recipeDTO);		
		ResponseEntity<RestApiResponse> response;
		try {
			recipeServices.save(recipeDTO);
			response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.CREATED, PropertiesConfig.environment.getProperty("recipe.save.success"),recipeDTO);
		} catch(Exception ex) {
			response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.FAILED, PropertiesConfig.environment.getProperty("recipe.save.error"),recipeDTO);
		}
		logger.debug("response:",response);	
		return response;
	}
	
	/**
	 * Update recipe
	 * @param recipeInfoBean
	 * @return ResponseEntity<RestApiResponse>
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "${recipe.api.desc.update}",  summary ="${recipe.api.summary.update}")
	public Object update(@Valid @RequestBody RecipeInfoDTO recipeDTO) {
		logger.info("invoke update()");
		logger.debug("invoke update() payload:",recipeDTO);	
		ResponseEntity<RestApiResponse> response;
		try {
			recipeServices.update(recipeDTO);
			response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.CREATED, PropertiesConfig.environment.getProperty("recipe.update.success"),recipeDTO);
		} catch(Exception ex) {
			response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.FAILED, PropertiesConfig.environment.getProperty("recipe.update.error"),recipeDTO);
		}
		logger.debug("response:",response);	
		return response;
	}
	
	/**
	 * Remove recipe
	 * @param recipeId
	 * @return ResponseEntity<RestApiResponse>
	 */
	@DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "${recipe.api.desc.delete}", summary ="${recipe.api.summary.delete}")
	public Object delete(@PathVariable(value = "id") String recipeId) {
		logger.info("invoke delete()");
		logger.debug("invoke delete() payload:",recipeId);	
		Boolean result = false;
		result = recipeServices.delete(recipeId);
		logger.debug("response:",result);
		return ResponseUtils.buildSuccessResponse(ConstantUtil.Status.SUCCESS, PropertiesConfig.environment.getProperty("recipe.delete.success"),result);
				
	}
	
	/**
	 *  Search recipes with different search criteria
	 * @param servedFor
	 * @param includeIngredient
	 * @param excludeIngredient
	 * @param instruction
	 * @return ResponseEntity<RestApiResponse>
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "${recipe.api.desc.search}", summary ="${recipe.api.summary.search}")
	@ResponseStatus(value = HttpStatus.OK)
	public Object search(@RequestParam(required = false, name="isVeg") Boolean isVeg,
			@RequestParam(required = false, name="servedFor") Integer servedFor,
			@RequestParam(required = false, name="includeIngredients") List<String> includeIngredients,
			@RequestParam(required = false, name="excludeIngredients") List<String> excludeIngredients,
			@RequestParam(required = false, name="instructions") String instruction
			) {
		logger.info("invoke search()");
		
		SearchInfoDTO searchDTO = new RecipeValidator().validateSearchCriteria(isVeg, servedFor, 
				includeIngredients, excludeIngredients, instruction);
		logger.debug("invoke search() payload:",searchDTO);	
		 ResponseEntity<RestApiResponse> response;
		 List<?> recipeList = recipeServices.search(searchDTO);
		 if(Optional.ofNullable(recipeList).isPresent() && recipeList.size()>0) {
			 response = ResponseUtils.buildSuccessResponse(ConstantUtil.Status.SUCCESS, PropertiesConfig.environment.getProperty("recipe.list.found"),recipeList);
		 } else {
			 throw new RecipeNotFoundException(PropertiesConfig.environment.getProperty("recipe.not.found"));			 
		 }
		 logger.debug("response:",response);	
		 return response;
	}
	
	

}
