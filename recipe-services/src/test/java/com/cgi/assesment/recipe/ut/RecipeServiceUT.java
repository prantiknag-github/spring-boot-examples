package com.cgi.assesment.recipe.ut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cgi.assesment.recipe.entity.RecipeEntityBean;
import com.cgi.assesment.recipe.model.RecipeInfoDTO;
import com.cgi.assesment.recipe.repository.RecipeRepository;
import com.cgi.assesment.recipe.services.RecipeServices;
import com.cgi.assesment.recipe.services.impl.RecipeServicesImpl;
import com.cgi.assesment.recipe.utilities.ConversionUtility;
/**
 * Represent Unit Test for Recipe Service
 * @author prantik
 *
 */
@ExtendWith(MockitoExtension.class)
public class RecipeServiceUT {
	
	@InjectMocks
	RecipeServices recipeServices = new RecipeServicesImpl();
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Test
	public void test_search_all_recipes() {
		List<RecipeEntityBean> recipeEntityList = new ArrayList<RecipeEntityBean>();
		RecipeEntityBean recipeBean1 = new RecipeEntityBean();	
		recipeBean1.setId("abcd-1234");
		recipeBean1.recipeId="abcd-1234";
		recipeBean1.recipeName = "Veg Jahlfrezi";
		recipeBean1.cuisine = "Indian";
		recipeBean1.isVegetarian = true;
		recipeBean1.instructions = "test oven";
		recipeBean1.noOfServings = 4;
		recipeBean1.ingredients = Arrays.asList("potatoes","brinjal");
		RecipeEntityBean recipeBean2 = new RecipeEntityBean();
		recipeBean2.setId("pqrs-1234");
		recipeBean2.recipeId="pqrs-1234";
		recipeBean2.recipeName = "Paneer Jahlfrezi";
		recipeBean2.cuisine = "Indian";
		recipeBean2.isVegetarian = true;
		recipeBean2.instructions = "cook in oven";
		recipeBean2.noOfServings = 3;
		recipeBean2.ingredients = Arrays.asList("potatoes","brinjal","paneer");
		
		recipeEntityList.add(recipeBean1);
		recipeEntityList.add(recipeBean2);
		when(recipeRepository.findAll()).thenReturn(recipeEntityList);
		
		List<RecipeEntityBean> rList = recipeRepository.findAll();
		assertEquals(2, rList.size());
        verify(recipeRepository, times(1)).findAll();
		
	}
	
	@Test
	public void test_save_recipe() {
		RecipeEntityBean recipeBean = new RecipeEntityBean();	
		recipeBean.recipeName = "Fish Steak";
		recipeBean.cuisine = "Continental";
		recipeBean.isVegetarian = false;
		recipeBean.instructions = "Slow cooked in steam";
		recipeBean.noOfServings = 4;
		recipeBean.ingredients = Arrays.asList("salmon","potatoes");
		RecipeInfoDTO recipeInfoDTO  = (RecipeInfoDTO) ConversionUtility.convertFromSourceToTargetBean(recipeServices.save(recipeBean),new RecipeInfoDTO());
		recipeInfoDTO.setRecipeIdReq("abcd-1234");
		assertEquals("abcd-1234", recipeInfoDTO.getRecipeIdReq());
		
	}
	
	@Test
	public void test_update_recipe() {
		RecipeEntityBean recipeBean = new RecipeEntityBean();	
		recipeBean.recipeId="abcd-1234";
		recipeBean.recipeName = "Fish Steak";
		recipeBean.cuisine = "Continental";
		recipeBean.isVegetarian = false;
		recipeBean.instructions = "Fried in mustard oil then slow cooked in steam.";
		recipeBean.noOfServings = 4;
		recipeBean.ingredients = Arrays.asList("salmon","potatoes");
		RecipeInfoDTO recipeInfoDTO  = (RecipeInfoDTO) ConversionUtility.convertFromSourceToTargetBean(recipeServices.update(recipeBean),new RecipeInfoDTO());
		assertEquals("Fried in mustard oil then slow cooked in steam.", recipeInfoDTO.getInstructionsReq());
		
	}
	
	
}
