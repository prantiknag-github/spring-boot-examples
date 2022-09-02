package com.cgi.assesment.recipe.services;

import java.util.List;
import java.util.Map;

import com.cgi.assesment.recipe.model.SearchInfoDTO;

public interface RecipeServices extends BaseServices {
	public List<?> search(SearchInfoDTO searchDTO); 
}
