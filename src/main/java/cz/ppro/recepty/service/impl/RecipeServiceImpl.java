package cz.ppro.recepty.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Photo;
import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.RecipeIngredient;
import cz.ppro.recepty.repository.IngredientRepository;
import cz.ppro.recepty.repository.PhotoRepository;
import cz.ppro.recepty.repository.RecipeIngredientRepository;
import cz.ppro.recepty.repository.RecipeRepository;
import cz.ppro.recepty.service.RecipeService;
import cz.ppro.recepty.validation.EntityValidator;

@Service
public class RecipeServiceImpl implements RecipeService {

	private static final Log logger = LogFactory.getLog(RecipeServiceImpl.class);

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeIngredientRepository recipeIngredientRepository;

	@Autowired
	private PhotoRepository photoRepository;

	@Override
	@Transactional
	public void setRating(Recipe recipe, int rating) {
		logger.info("Prepocitavam hodnoceni u receptu ID " + recipe.getIdRecipe());
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
		logger.info("Nove hodnoceni: " + recipe.getRating());

		recipeRepository.save(recipe);
	}

	@Override
	@Transactional
	public Ingredient createIngredient(String ingredientName) {
		logger.info("Ukladam novou ingredienci s nazvem " + ingredientName);

		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientName(ingredientName);
		ingredientRepository.save(ingredient);

		return ingredient;
	}

	@Override
	@Transactional
	public void addIngredientToRecipe(RecipeIngredient recipeIngredient) {
		EntityValidator.checkRecipeIngredient(recipeIngredient);
		recipeIngredientRepository.save(recipeIngredient);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recipe> showRecipesByCategory(String category) {
		List<Recipe> recipes = recipeRepository.findByCategory(category);
		return recipes;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recipe> findRecipesByAllIngredients(List<Ingredient> ingredients) {
		List<Recipe> recipes = new ArrayList<>();
		List<Recipe> foundRecipes = new ArrayList<>();

		for (Ingredient ingredient : ingredients) {
			recipes.addAll(recipeRepository.findByRecipeIngredients_Ingredient(ingredient));
		}

		for (Recipe recipe : recipes) {
			int count = Collections.frequency(recipes, recipe);
			if (count == ingredients.size()) {
				foundRecipes.add(recipe);
			}
		}
		return foundRecipes;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recipe> findRecipesByIngredients(List<Ingredient> ingredients) {
		List<Recipe> recipes = new ArrayList<>();
		List<Recipe> foundRecipes = new ArrayList<>();

		for (Ingredient ingredient : ingredients) {
			recipes.addAll(recipeRepository.findByRecipeIngredients_Ingredient(ingredient));
		}

		recipeLoop: for (Recipe recipe : recipes) {
			for (RecipeIngredient ri : recipe.getRecipeIngredients()) {
				if (!ingredients.contains(ri.getIngredient())) {
					continue recipeLoop;
				}
			}
			foundRecipes.add(recipe);
		}
		return foundRecipes;
	}

	@Override
	@Transactional
	public void createRecipe(Recipe recipe, AppUser user) {
		recipe.setAuthor(user);
		EntityValidator.checkRecipe(recipe);
		recipeRepository.save(recipe);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recipe> getRecipesSortedByRating() {
		List<Recipe> recipes = recipeRepository.findAllByOrderByRatingAsc();
		return recipes;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<RecipeIngredient> getAllIngredients(Recipe recipe) {
		return recipeIngredientRepository.findByRecipe(recipe);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recipe> getAllRecipesByUser(AppUser user) {
		return recipeRepository.findByAuthor(user);
	}

	@Override
	public Recipe findById(Long id) {
		return recipeRepository.findOne(id);
	}

	@Override
	@Transactional
	public void deleteRecipe(Recipe recipe) {
		recipeRepository.delete(recipe);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Photo> getPhotosByRecipe(Recipe recipe) {
		return photoRepository.findByRecipe(recipe);
	}

	@Override
	public List<Recipe> findRecipesByName(String name) {
		return recipeRepository.findByNameLike(name);
	}

	@Override
	public void deleteRecipeIngredient(Long id) {
		recipeIngredientRepository.delete(id);
	}

}
