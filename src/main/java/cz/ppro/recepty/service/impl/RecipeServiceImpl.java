package cz.ppro.recepty.service.impl;

import java.util.List;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.RecipeIngredient;
import cz.ppro.recepty.repository.IngredientRepository;
import cz.ppro.recepty.repository.RecipeIngredientRepository;
import cz.ppro.recepty.repository.RecipeRepository;
import cz.ppro.recepty.service.RecipeService;
import cz.ppro.recepty.validation.EntityValidator;

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

		rateCount++;
		recipe.setRateCount(rateCount);

		recipeRepository.save(recipe);
	}

	@Override
	public Ingredient createIngredient(String ingredientName) {
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientName(ingredientName);
		ingredientRepository.save(ingredient);

		return ingredient;
	}

	@Override
	public void addIngredientToRecipe(RecipeIngredient recipeIngredient) {
		EntityValidator.checkRecipeIngredient(recipeIngredient);
		recipeIngredientRepository.save(recipeIngredient);
	}

	@Override
	public List<Recipe> showRecipesByCategory(String category) {
		return recipeRepository.findByCategory(category);
	}

	@Override
	public String showRecipesByIngredients(String[] ingredient) {
		return null;
	}

	@Override
	@Transactional
	public void createRecipe(Recipe recipe) {
		EntityValidator.checkRecipe(recipe);
		recipeRepository.save(recipe);
	}

	@Override
	public List<Recipe> getRecipesSortedByRating() {
		return recipeRepository.findAllByOrderByRatingAsc();
	}

	@Override
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<RecipeIngredient> getAllIngredients(Recipe recipe) {
		return recipeIngredientRepository.findByRecipe(recipe);
	}

	@Override
	public List<Recipe> getAllRecipesByUser(AppUser user) {
		return null;
	}

	@Override
	@Transactional
	public void deleteRecipe(Recipe recipe) {
		recipeRepository.delete(recipe);
	}

}
