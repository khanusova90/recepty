package cz.ppro.recepty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	@Query("select r from Recipe r join r.categories c where c = :category")
	public List<Recipe> findByCategory(@Param("category") String category);

	public List<Recipe> findAllByOrderByRatingAsc();

	public List<Recipe> findByRecipeIngredients_Ingredient(Ingredient ingredient);

	// public List<Recipe> findRecipeByIngredients(List<Ingredient>
	// ingredients);

	public List<Recipe> findByAuthor(AppUser author);

}
