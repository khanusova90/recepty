package cz.ppro.recepty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	// FIXME: nevim, ktera z tech metod bude fungovat

	@Query("select distinct r from Recipe r join r.categories c where c = :category")
	public List<Recipe> findByCategory(String category);

	public List<Recipe> findAllByCategory(String category);

	// @Query("SELECT DISTINCT t FROM RecipeRaw rr JOIN rr.listOfIngredients t "
	// + "WHERE t LIKE CONCAT('%', :ing_name, '%')")
	// List<String> findIngredientsByLikeName(@Param("ing_name") String name);

	public List<Ingredient> findRecipeIngredientsByIdRecipe(Long idRecipe);

	public List<Recipe> findAllOrderByRatingAsc();

}
