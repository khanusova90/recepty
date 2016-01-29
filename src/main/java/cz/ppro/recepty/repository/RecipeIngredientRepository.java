package cz.ppro.recepty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.ppro.recepty.domain.RecipeIngredient;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

	public List<RecipeIngredient> findByRecipe_IdRecipe(Long idRecipe);

}
