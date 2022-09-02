package com.cgi.assesment.recipe.services;

import java.util.List;
import java.util.Map;
/**
 * Common interface for CRUD operation
 * @author prantik
 *
 */
public interface BaseServices {
	
	public <T> T save(T infoBean);
	public <T> T update(T infoBean);
	public Boolean delete(String id);	

}
