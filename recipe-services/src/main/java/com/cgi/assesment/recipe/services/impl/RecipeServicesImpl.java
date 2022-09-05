package com.cgi.assesment.recipe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgi.assesment.recipe.entity.RecipeEntityBean;
import com.cgi.assesment.recipe.exceptions.RecipeNotFoundException;
import com.cgi.assesment.recipe.model.SearchInfoDTO;
import com.cgi.assesment.recipe.repository.RecipeRepository;
import com.cgi.assesment.recipe.services.RecipeServices;
import com.cgi.assesment.recipe.utilities.ConversionUtility;
import com.cgi.assesment.recipe.utilities.RecipeConversionUtility;
import com.cgi.assesment.recipe.model.RecipeInfoDTO;
/**
 * Represent Service class for Business Logic implementation of Recipe
 * @author prantik
 *
 */
@Service
@Transactional
public class RecipeServicesImpl implements RecipeServices {
	
	@Autowired
	private RecipeRepository recipeRepo;

	/**
	 * save recipe in the database
	 */
	@Override
	public <RecipeInfoDTO> RecipeInfoDTO save(RecipeInfoDTO recipeDTO) {
		RecipeEntityBean recipeEntityBean = new RecipeEntityBean();
		recipeEntityBean = (RecipeEntityBean) ConversionUtility.convertFromSourceToTargetBean(recipeDTO, recipeEntityBean);
		recipeRepo.save(recipeEntityBean);
		recipeEntityBean.setRecipeId(recipeEntityBean.getId());
		recipeDTO = (RecipeInfoDTO) ConversionUtility.convertFromSourceToTargetBean(recipeEntityBean, recipeDTO);
		return recipeDTO;
	}

	/**
	 * update receipe in the database
	 */
	@Override
	public <RecipeInfoDTO> RecipeInfoDTO update(RecipeInfoDTO receipeDTO) throws RecipeNotFoundException {
		RecipeEntityBean receipeEntityBean = new RecipeEntityBean();
		receipeEntityBean = (RecipeEntityBean) ConversionUtility.convertFromSourceToTargetBean(receipeDTO, receipeEntityBean);
		receipeEntityBean.setId(receipeEntityBean.getRecipeId());
		recipeRepo.save(receipeEntityBean);
		receipeEntityBean.setRecipeId(receipeEntityBean.getId());
		receipeDTO = (RecipeInfoDTO) ConversionUtility.convertFromSourceToTargetBean(receipeEntityBean,receipeDTO);
		return receipeDTO;
	}

	/**
	 * delete receipe from the database
	 */
	@Override
	public Boolean delete(String id) {
		AtomicBoolean result = new AtomicBoolean(false);
		if(recipeRepo.existsById(id)) {
			recipeRepo.deleteById(id);			
			result.set(true);
		} else {
			throw new RecipeNotFoundException("No Recipe information details found!! Error in Recipe information deletion!!");
		}
		return result.get();
		
	}

	/**
	 * search receipes with different search criteria
	 */
	@Override
	public List<RecipeInfoDTO> search(SearchInfoDTO searchDTO) {
		String queryText = "";
		List<RecipeEntityBean> receipeList;
		String searchBy = "";
		if(Optional.ofNullable(searchDTO).isEmpty()) {
			receipeList = recipeRepo.findAll();
		} else {
			if(Optional.ofNullable(searchDTO.getIsVegetarian()).isPresent()) {
				searchBy+="A";
			}
			if(Optional.ofNullable(searchDTO.getNoOfServings()).isPresent()) {
				searchBy+="B";
			}
			if(Optional.ofNullable(searchDTO.getIncludeIngredients()).isPresent() && searchDTO.getIncludeIngredients().size()>0) {
				searchBy+="C";
			}
			if(Optional.ofNullable(searchDTO.getExcludeIngredients()).isPresent() && searchDTO.getExcludeIngredients().size()>0) {
				searchBy+="D";
			}
			if(Optional.ofNullable(searchDTO.getInstructions()).isPresent()) {
				searchBy+="E";
			} 
			
			//search criteria implemented as per assignment
			switch(searchBy) {				
				case "A":
					//search for vegetarian receipe
					receipeList = recipeRepo.findByIsVegetarian(searchDTO.getIsVegetarian());
					break;
				case "B":
					//search for no of serves
					receipeList = recipeRepo.findByNoOfServings(searchDTO.getNoOfServings());
					break;
				case "C":
					//search for include ingredients
					receipeList = recipeRepo.findByIngredientsContains(searchDTO.getIncludeIngredients());
					break;
				case "D":
					//search for exclude ingredients
					receipeList = recipeRepo.findByIngredientsNotContains(searchDTO.getExcludeIngredients());
					break;
				case "E":
					//search for instructions
					receipeList = recipeRepo.findByInstructionsContaining(searchDTO.getInstructions());
					break;
				case "BC":
					//search for no. of serves and include ingredients
					receipeList = recipeRepo.findByNoOfServingsAndIngredientContains(searchDTO.getNoOfServings(),searchDTO.getIncludeIngredients());
					break;
				case "DE":
					//search for exclude ingredients and instruction contains
					receipeList = recipeRepo.findByIngredientNotContainsAndInstructionContains(searchDTO.getExcludeIngredients(), searchDTO.getInstructions());
					break;
				//TODO Other combinations for search criteria  can be implemented	
				default:
					receipeList = new ArrayList<RecipeEntityBean>();
			}
		}
		List<RecipeInfoDTO> recipeInfoDTOs = null;
		if(Optional.ofNullable(receipeList).isPresent() && receipeList.size()>0) {
			recipeInfoDTOs = RecipeConversionUtility.convertFromEntityToDTO(receipeList);
		}
		return recipeInfoDTOs;
	}

}
