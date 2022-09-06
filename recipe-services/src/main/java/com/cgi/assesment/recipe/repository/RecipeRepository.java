package com.cgi.assesment.recipe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cgi.assesment.recipe.entity.RecipeEntityBean;



/**
 * Represent Recipe Repository used for persist, update recipe data and contains various search criteria
 * implemented with @Query for custom search
 * @author prantik
 *
 */
public interface RecipeRepository extends MongoRepository<RecipeEntityBean, String> {
	/**
	 * search by dish is vegetarian or not
	 * @param isVegetarian
	 * @return list of recipes
	 */
	List<RecipeEntityBean> findByIsVegetarian(Boolean isVegetarian);
	
	/**
	 * search by no. of servings
	 * @param noOfServings
	 * @return list of recipes
	 */
	List<RecipeEntityBean> findByNoOfServings(Integer noOfServings);	
	
	/**
	 * search by instruction text contains specific word or statement
	 * @param instructions
	 * @return list of recipes
	 */
	List<RecipeEntityBean> findByInstructionsContaining(String instructions);
	
	/**
	 * search by include ingredients
	 * @param ingredients
	 * @return list of recipes
	 */
	@Query("{ 'ingredients' : { $in: :#{#ingredients} } }")
	List<RecipeEntityBean> findByIngredientsContains(List<String> ingredients);
	
	/**
	 * search by exclude ingredients
	 * @param ingredients
	 * @return list of recipes
	 */
	@Query("{ 'ingredients' : { $nin: :#{#ingredients} } }")
	List<RecipeEntityBean> findByIngredientsNotContains(List<String> ingredients);
	
	/**
	 * search by no of serving and include ingredients
	 * @param noOfServings
	 * @param ingredients
	 * @return
	 */
	@Query("{ 'noOfServings' : :#{#noOfServings} , 'ingredients' : { $in: :#{#ingredients} } }")
	List<RecipeEntityBean> findByNoOfServingsAndIngredientContains(@Param("noOfServings") Integer noOfServings, @Param("ingredients") List<String> ingredients);
	
	/**
	 * search by exclude ingredients and contains text in instructions
	 * @param ingredients
	 * @param instructions
	 * @return
	 */
	@Query("{ 'ingredients' : { $nin: :#{#ingredients} }, 'instructions' : { $regex : :#{#instructions} } }")
	List<RecipeEntityBean> findByIngredientNotContainsAndInstructionContains(@Param("ingredients") List<String> ingredients,@Param("instructions") String instructions );
}
