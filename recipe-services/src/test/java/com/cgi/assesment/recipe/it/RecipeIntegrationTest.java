package com.cgi.assesment.recipe.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cgi.assesment.recipe.controller.RecipeController;
import com.cgi.assesment.recipe.model.RecipeInfoDTO;
import com.cgi.assesment.recipe.utilities.RestApiResponse;
/**
 * 
 * Represent Integration Test for Recipe Service
 * @author prantik
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeIntegrationTest {

	@Autowired
	RecipeController recipeController;
	
	@Test
	public void test_save_read_delete() {
		RecipeInfoDTO recipeDTO = new RecipeInfoDTO();
		recipeDTO.recipeNameReq = "Veg Jahlfrezi";
		recipeDTO.cuisineReq = "Indian";
		recipeDTO.isVegetarianReq = true;
		recipeDTO.instructionsReq = "test oven";
		recipeDTO.noOfServingsReq = 4;
		recipeDTO.ingredientsReq = Arrays.asList("potatoes","brinjal");
		
		HttpEntity<RestApiResponse<RecipeInfoDTO>> respSave = (HttpEntity<RestApiResponse<RecipeInfoDTO>>) recipeController.save(recipeDTO);
		HttpEntity<RestApiResponse<List<RecipeInfoDTO>>>  respSearch = (HttpEntity<RestApiResponse<List<RecipeInfoDTO>>>) recipeController.search(null, null, null, null, null);
		Assertions.assertThat(respSearch.getBody().getResponseData()).last().hasFieldOrPropertyWithValue("recipeIdReq", respSave.getBody().getResponseData().recipeIdReq);
		HttpEntity<RestApiResponse>  respDelete = (HttpEntity<RestApiResponse>) recipeController.delete(respSave.getBody().getResponseData().getRecipeIdReq());
		assertEquals("success",respDelete.getBody().getStatus());
	}
}
