package cz.ppro.recepty.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.domain.Category;
import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.RecipeIngredient;
import cz.ppro.recepty.service.IngredientService;
import cz.ppro.recepty.service.RecipeService;
import cz.ppro.recepty.utils.UserUtils;

@Controller
@RequestMapping("/recipes")
class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showRecipes(Model model) {
		model.addAttribute("recipes", recipeService.getAllRecipes());
		return "listedRecipes";
	}

	@RequestMapping(value = "/{recipeType}")
	public void showRecipesByCategory(HttpSession session, Model model, @PathVariable("recipeType") String recipeType) {
		model.addAttribute("ingredients", recipeService.showRecipesByCategory(recipeType));
	}

	@RequestMapping(value = "/detail")
	public String showRecipesDetail(Model model, HttpSession session, @RequestParam("recipe") Recipe recipe) {
		model.addAttribute("recipeIngredients", recipe.getRecipeIngredients());
		model.addAttribute("recipe", recipe);
		return "recipeDetail";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String addRecipe(Model model) {
		Recipe recipe = new Recipe();
		model.addAttribute("recipe", recipe);
		model.addAttribute("categories", Category.values());
		model.addAttribute("recipeIngredients", new ArrayList<RecipeIngredient>());
		model.addAttribute("ingredients", ingredientService.getAll());

		return "recipes/recipeAddForm";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
	public String doAddRecipe(Recipe recipe, @RequestParam("ingredients") List<RecipeIngredient> ingredients,
			Model model) {
		AppUser user = UserUtils.getActualUser();
		recipeService.createRecipe(recipe, ingredients, user);
		model.addAttribute("recipes", recipeService.getAllRecipesByUser(user));
		return "redirect:/listedRecipes";
	}

	// @RequestMapping(value = "/doDeleteRecipe", method = RequestMethod.POST)
	// public String deleteRecipe(Model model, HttpSession session,
	// @RequestParam("recipe") Recipe recipe) {
	// recipeService.deleteRecipe(recipe);
	// AppUser user = (AppUser) session.getAttribute("user");
	// model.addAttribute("recipes", recipeService.getAllRecipesByUser(user));
	// return "listedRecipes";
	// }

	@RequestMapping(value = "/searchByIngredientAll")
	public String showDishes(Model model, Locale locale) {
		model.addAttribute("recipes", null);
		model.addAttribute("selectedIngredients", messageSource.getMessage("insertIngredients", null, locale));
		return "searchByIngredients";
	}

	@RequestMapping(value = "/searchByIngredientAll", method = RequestMethod.POST)
	public String showDishes(Model model, @ModelAttribute("selectedIngredients") String ingredientsString) {
		List<Ingredient> ingredients = ingredientService.splitIngredients(ingredientsString);
		List<Recipe> recipes = recipeService.findRecipesByAllIngredients(ingredients);
		model.addAttribute("recipes", recipes);
		return "listedRecipes";
	}

	@RequestMapping(value = "/searchByName", method = RequestMethod.GET)
	public String findByName(Model model, Locale locale) {
		model.addAttribute("name", messageSource.getMessage("insertName", null, locale));
		model.addAttribute("recipes", null);
		return "searchByName";
	}

	@RequestMapping(value = "/searchByName", method = RequestMethod.POST)
	public String findByName(Model model, @ModelAttribute("name") String name) {
		List<Recipe> recipes = recipeService.findRecipesByName(name);
		model.addAttribute("recipes", recipes);
		return "listedRecipes";
	}

	@RequestMapping(value = "/searchByIngredient", method = RequestMethod.GET)
	public String findByIngredients(Model model, Locale locale) {
		model.addAttribute("selectedIngredients", messageSource.getMessage("insertIngredients", null, locale));
		return "searchByIngredients";
	}

	@RequestMapping(value = "/searchByIngredient", method = RequestMethod.POST)
	public String findByIngredients(Model model, @ModelAttribute("ingredients") String ingredientsString) {
		List<Ingredient> ingredients = ingredientService.splitIngredients(ingredientsString);
		List<Recipe> recipes = recipeService.findRecipesByIngredients(ingredients);
		model.addAttribute("recipes", recipes);
		return "listedRecipes";
	}
}
