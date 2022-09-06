package com.cgi.assesment.recipe.services;

import java.util.List;

import com.cgi.assesment.recipe.model.RecipeInfoDTO;
import com.cgi.assesment.recipe.model.SearchInfoDTO;
/**
 * Represent Recipe specific CRUD interface
 * @author prantik
 *
 */
public interface RecipeServices extends BaseServices {
	public List<RecipeInfoDTO> search(SearchInfoDTO searchDTO); 
}
