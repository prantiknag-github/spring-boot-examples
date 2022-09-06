package com.cgi.assesment.recipe.utilities;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.cgi.assesment.recipe.controller.RecipeController;
import com.cgi.assesment.recipe.entity.RecipeEntityBean;
import com.cgi.assesment.recipe.model.RecipeInfoDTO;

import ch.qos.logback.classic.Logger;
/**
 * Represent Conversion from source bean to target bean
 * @author prantik
 *
 */
public class ConversionUtility {

	private static Logger logger = (Logger) LoggerFactory.getLogger(ConversionUtility.class);
	
	public static <T> T convertFromSourceToTargetBean(T sourceBean, T targetBean) {		
		logger.info("invoke convertFromSourceToTargetBean()");
		logger.debug("invoke convertFromSourceToTargetBean() source payload:",sourceBean);
		logger.debug("invoke convertFromSourceToTargetBean() target payload:",targetBean);
		List<Field> sourcePropList = Arrays.asList(sourceBean.getClass().getDeclaredFields());		
		List<Field> targetPropList = Arrays.asList(targetBean.getClass().getDeclaredFields());
		
		targetPropList.stream().forEach(target -> {
			try {
				String attributeName = target.getName().replace("Req", "").toLowerCase();				
				sourcePropList.stream().forEach(src-> {
					if(src.getName().toLowerCase().contains(attributeName)) {	
						target.setAccessible(true);								
						try {
							target.set(targetBean, src.get(sourceBean));
						} catch (IllegalArgumentException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
						
					}
				});
			}  catch (SecurityException e) {
				// TODO Auto-generated catch block
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		});
		logger.debug("response:",targetBean);	
		return targetBean;
	}
	
	
	
	
/*	public static void main(String args[]) {
		RecipeEntityBean receipeEntityBean = new RecipeEntityBean();
		RecipeInfoDTO receipeInfoBean = new RecipeInfoDTO();
		receipeInfoBean.isVegetarianReq = true;
		receipeInfoBean.instructionsReq = "test oven";
		receipeInfoBean.noOfServingsReq = 4;
		receipeInfoBean.ingredientsReq = Arrays.asList("potatoes","brinjal");
		receipeEntityBean = (RecipeEntityBean) ConversionUtility.convertFromSourceToTargetBean(receipeInfoBean, receipeEntityBean);
		System.out.println(receipeEntityBean.toString());
		
	}
*/
}
