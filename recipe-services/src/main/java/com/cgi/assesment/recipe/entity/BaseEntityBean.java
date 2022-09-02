package com.cgi.assesment.recipe.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * Represent for Base entity
 * @author prantik
 */
@Getter
@Setter
@ToString
public class BaseEntityBean<ID extends Serializable> {
	
	@Id
	ID id;	
	

}
