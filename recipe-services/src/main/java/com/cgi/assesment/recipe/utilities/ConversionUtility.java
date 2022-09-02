package com.cgi.assesment.recipe.utilities;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import com.cgi.assesment.recipe.entity.RecipeEntityBean;
import com.cgi.assesment.recipe.model.RecipeInfoDTO;
/**
 * Represent Conversion from request bean to entity bean
 * @author prantik
 *
 */
public class ConversionUtility {


	public static <T> T convertFromSoureceToTargetBean(T sourceBean, T targetBean) {		
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
				e.printStackTrace();
			}
		});
		return targetBean;
	}
	
	
	
	public static void main(String args[]) {
		RecipeEntityBean receipeEntityBean = new RecipeEntityBean();
		RecipeInfoDTO receipeInfoBean = new RecipeInfoDTO();
		receipeInfoBean.isVegetarianReq = true;
		receipeInfoBean.instructionsReq = "test oven";
		receipeInfoBean.noOfServingsReq = 4;
		receipeInfoBean.ingredientsReq = Arrays.asList("potatoes","brinjal");
		receipeEntityBean = (RecipeEntityBean) ConversionUtility.convertFromSoureceToTargetBean(receipeInfoBean, receipeEntityBean);
		System.out.println(receipeEntityBean.toString());
		
	}

}