package com.cgi.assesment.recipe.validator;

import java.util.List;
import java.util.Optional;

import com.cgi.assesment.recipe.model.SearchInfoDTO;
/**
 * Represent to validate search criteria
 * @author prantik
 *
 */
public class RecipeValidator {
	
	public SearchInfoDTO validateSearchCriteria(Boolean isVeg,Integer servedFor,List<String> includeIngredient,
			List<String> excludeIngredient,String instruction) {
		SearchInfoDTO searchDTO = null;
		if(Optional.ofNullable(isVeg).isPresent()) {
			if(!Optional.ofNullable(searchDTO).isPresent()) {
				searchDTO = new SearchInfoDTO();
			}
			searchDTO.setIsVegetarian(isVeg);
		}
		if(Optional.ofNullable(servedFor).isPresent()) {
			if(!Optional.ofNullable(searchDTO).isPresent()) {
				searchDTO = new SearchInfoDTO();
			}
			searchDTO.setNoOfServings(servedFor);
		}
		if(Optional.ofNullable(instruction).isPresent()) {
			if(!Optional.ofNullable(searchDTO).isPresent()) {
				searchDTO = new SearchInfoDTO();
			}
			searchDTO.setInstructions(instruction);
		}
		if(Optional.ofNullable(includeIngredient).isPresent() && includeIngredient.size()>0) {
			if(!Optional.ofNullable(searchDTO).isPresent()) {
				searchDTO = new SearchInfoDTO();
			}
			searchDTO.setIncludeIngredients(includeIngredient);
		}
		if(Optional.ofNullable(excludeIngredient).isPresent() && excludeIngredient.size()>0) {
			if(!Optional.ofNullable(searchDTO).isPresent()) {
				searchDTO = new SearchInfoDTO();
			}
			searchDTO.setExcludeIngredients(excludeIngredient);
		}
		return searchDTO;
		
	}

}
