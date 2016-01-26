package cz.ppro.recepty.service.impl;

import cz.ppro.recepty.dao.RecipeDao;
import cz.ppro.recepty.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.RecipeIngredient;
import cz.ppro.recepty.repository.IngredientRepository;
import cz.ppro.recepty.repository.RecipeIngredientRepository;
import cz.ppro.recepty.repository.RecipeRepository;
import cz.ppro.recepty.service.RecipeService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

	@Override
	public void setRating(Recipe recipe, int rating) {
		int rateCount = recipe.getRateCount();
		if (rateCount == 0) {
			recipe.setRating(rating);
		} else {
			float totalRating = recipe.getRating() * rateCount;
			float newRating = (totalRating + rating) / (rateCount + 1);
			recipe.setRating(newRating);
		}
    @Autowired
    private RecipeDao recipeDao;

		rateCount++;
		recipe.setRateCount(rateCount);

    /*
     * (non-Javadoc)
     *
     * @see cz.ppro.recepty.service.RecipeService#setRating(cz.ppro.recepty.
     * domain.Recipe, int)
     */
    @Override
    public void setRating(Recipe recipe, int rating) {
        int rateCount = recipe.getRateCount();
        if (rateCount == 0) {
            recipe.setRating(rating);
        } else {
            float totalRating = recipe.getRating() * rateCount;
            float newRating = (totalRating + rating) / (rateCount + 1);
            recipe.setRating(newRating);
        }
		recipeRepository.save(recipe);

        rateCount++;
        recipe.setRateCount(rateCount);

        // TODO: dao.save(recipe);

    }

	@Override
	@Transactional
	public void addIngredientToRecipe(RecipeIngredient recipeIngredient) {
		// TODO: Jeste nevim, jestli pujde z view predat rovnou objekt, nebo jen
		// jeho parametry a pak to tu budu muset vytvorit
    @Override
    public Ingredient createIngredient(String ingredientName) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientName);
        ingredientRepository.save(ingredient);

        return ingredient;
    }

    @Override
    public void addIngredientToRecipe(RecipeIngredient recipeIngredient) {
        // TODO: Jeste nevim, jestli pujde z view predat rovnou objekt, nebo jen
        // jeho parametry a pak to tu budu muset vytvorit

        checkRecipeIngredient(recipeIngredient);
        recipeIngredientRepository.save(recipeIngredient);
    }

    /**
     * Zkontroluje, jestli se ingredience k receptu spravne uklada
     *
     * @param ri
     */
    private void checkRecipeIngredient(RecipeIngredient ri) {
        if (ri.getRecipe() == null) {
            throw new IllegalArgumentException("Není vyplnìn recept, ke kterému se má ingredience uložit!");
        }
        if (ri.getIngredient() == null) {
            throw new IllegalArgumentException("Není vyplnìna ingredience, která se má receptu pøiøadit!");
        }
        if (ri.getAmount() == null || ri.getUnit() == null) {
            throw new IllegalArgumentException("Není vyplnìno množství ingredience, která se má pøiøadit k receptu!");
        }
    }

    @Override
    public String showRecipesByCategory(String category) {
        return null;
    }

    @Override
    public String showRecipesByIngredients(String[] ingredient) {
        return null;
    }


    @Override
    @Transactional
    public void createRecipe(Recipe recipe) {
        recipeDao.addRecipe(recipe);
    }

    @Override
    @Transactional
    public void deleteRecipe(int id) {
        recipeDao.deleteRecipe(id);
    }


}
