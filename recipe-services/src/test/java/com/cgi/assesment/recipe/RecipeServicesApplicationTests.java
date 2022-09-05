package com.cgi.assesment.recipe;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cgi.assesment.recipe.controller.RecipeController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeServicesApplicationTests {
	
	@Autowired
	RecipeController recipeController;

	@Test
	void contextLoads() {
		assertThat(recipeController).isNotNull();
	}

}
