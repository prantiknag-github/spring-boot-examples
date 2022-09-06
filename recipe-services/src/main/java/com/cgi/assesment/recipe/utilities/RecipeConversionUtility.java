package com.cgi.assesment.recipe.utilities;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;

import com.cgi.assesment.recipe.controller.RecipeController;
import com.cgi.assesment.recipe.entity.RecipeEntityBean;
import com.cgi.assesment.recipe.model.RecipeInfoDTO;

import ch.qos.logback.classic.Logger;
/**
 * Represent conversion utility from entity to DTO
 * @author prantik
 *
 */
public class RecipeConversionUtility {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(RecipeConversionUtility.class);
	
	public static List<RecipeInfoDTO> convertFromEntityToDTO(List<RecipeEntityBean> recipeEntityBeans ) {
		logger.info("invoke convertFromEntityToDTO()");
		logger.debug("invoke convertFromEntityToDTO() payload:",recipeEntityBeans);
		 List<RecipeInfoDTO> recipeInfoDTOs = recipeEntityBeans.stream()
				.map(rec->  {
					rec.setRecipeId(rec.getId());
					return (RecipeInfoDTO) ConversionUtility.convertFromSourceToTargetBean(rec, new RecipeInfoDTO());
				}).collect(Collectors.toList());
		 logger.debug("response:",recipeInfoDTOs);	
		 return recipeInfoDTOs;

	}

}
