package cz.ppro.recepty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cz.ppro.recepty.domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	@Query("select r from Recipe r join r.categories c where c = :category")
	public List<Recipe> findByCategory(@Param("category") String category);

	// @Query("SELECT DISTINCT t FROM RecipeRaw rr JOIN rr.listOfIngredients t "
	// + "WHERE t LIKE CONCAT('%', :ing_name, '%')")
	// List<String> findIngredientsByLikeName(@Param("ing_name") String name);

	public List<Recipe> findAllByOrderByRatingAsc();

}
