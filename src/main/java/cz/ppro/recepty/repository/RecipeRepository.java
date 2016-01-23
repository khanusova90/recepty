package cz.ppro.recepty.repository;

import org.springframework.stereotype.Repository;

import cz.ppro.recepty.domain.Recipe;

@Repository
public interface RecipeRepository {

	public Recipe findById(Long idRecipe);

}
