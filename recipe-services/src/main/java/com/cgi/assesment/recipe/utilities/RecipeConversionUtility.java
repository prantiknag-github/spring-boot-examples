package com.cgi.assesment.recipe.utilities;

import java.util.List;
import java.util.stream.Collectors;

import com.cgi.assesment.recipe.entity.RecipeEntityBean;
import com.cgi.assesment.recipe.model.RecipeInfoDTO;

public class RecipeConversionUtility {
	
	public static List<RecipeInfoDTO> convertFromEntityToDTO(List<RecipeEntityBean> recipeEntityBeans ) {
		return recipeEntityBeans.stream()
				.map(rec->  {
					rec.setRecipeId(rec.getId());
					return (RecipeInfoDTO) ConversionUtility.convertFromSourceToTargetBean(rec, new RecipeInfoDTO());
				}).collect(Collectors.toList());
	}

}
