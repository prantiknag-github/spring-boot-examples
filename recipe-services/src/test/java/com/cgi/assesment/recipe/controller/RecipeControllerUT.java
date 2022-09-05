package com.cgi.assesment.recipe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import com.cgi.assesment.recipe.model.RecipeInfoDTO;
import com.cgi.assesment.recipe.model.SearchInfoDTO;
import com.cgi.assesment.recipe.services.RecipeServices;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerUT {

	@MockBean
	RecipeServices recipeServices;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testSearchByCriteria() throws Exception {
		RecipeInfoDTO recipeDTO = new RecipeInfoDTO();
		recipeDTO.recipeIdReq="abcd-1234";
		recipeDTO.recipeNameReq = "Veg Jahlfrezi";
		recipeDTO.cuisineReq = "Indian";
		recipeDTO.isVegetarianReq = true;
		recipeDTO.instructionsReq = "test oven";
		recipeDTO.noOfServingsReq = 4;
		recipeDTO.ingredientsReq = Arrays.asList("potatoes","brinjal");
		List<RecipeInfoDTO> recipeList = new ArrayList<RecipeInfoDTO>();
		recipeList.add(recipeDTO);
		
		SearchInfoDTO searchDTO = null;		
		Mockito.doReturn(recipeList).when(recipeServices).search(searchDTO);
		mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
		
	}
}
