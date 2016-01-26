package cz.ppro.recepty.dao;

import cz.ppro.recepty.domain.Recipe;

import java.util.List;

/**
 * Created by tynak_000 on 26/01/2016.
 */
public interface RecipeDao {

    public void addRecipe(Recipe recipe);

    public void updateRecipeInfo(Recipe recipe);

    /**
     * Returns all recipes
     * @return
     */
    public List<Recipe> listRecipe();

    /**
     * Get recipe by id
     * @param id
     * @return
     */
    public Recipe getRecipeById(int id);

    public void deleteRecipe(int id);

}

