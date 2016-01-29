package cz.ppro.recepty.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.RecipeIngredient;
import cz.ppro.recepty.repository.IngredientRepository;
import cz.ppro.recepty.repository.RecipeIngredientRepository;
import cz.ppro.recepty.repository.RecipeRepository;
import cz.ppro.recepty.service.RecipeService;
import cz.ppro.recepty.validation.EntityValidator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
		EntityValidator.checkRecipe(recipe);
		recipeRepository.save(recipe);
	}
	@Override
	public List<Recipe> getRecipesSortedByRating() {
		return recipeRepository.findAll(sortByIdRating());
	}
	private Sort sortByIdRating() {

		return new Sort(Sort.Direction.ASC, "rating");
	}

	@Override
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	@Override
	public List<Ingredient> getAllIngredients(Long idRecipe) {
		return null;
	}

	@Override
	@Transactional
	public void deleteRecipe(Long id) {
		Recipe recipe = recipeRepository.findOne(id);
		recipeRepository.delete(recipe);
	}

}
