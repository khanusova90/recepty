package cz.ppro.recepty.dao.impl;

import cz.ppro.recepty.dao.RecipeDao;
import cz.ppro.recepty.domain.Recipe;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tynak_000 on 26/01/2016.
 */
@Repository
public class RecipeDaoImpl implements RecipeDao {
    @Override
    public void addRecipe(Recipe recipe) {
        //TODO: impleement
    }

    @Override
    public void updateRecipeInfo(Recipe recipe) {
        //TODO: impleement

    }

    @Override
    public List<Recipe> listRecipe() {
        return null;
        //TODO: impleement
    }

    @Override
    public Recipe getRecipeById(int id) {
        return null;
        //TODO: impleement
    }

    @Override
    public void deleteRecipe(int id) {
    //TODO: impleement
    }
}
