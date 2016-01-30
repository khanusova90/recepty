package cz.ppro.recepty.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
@RequestMapping("/recipes")
class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private IngredientService ingredientService;

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

		return "recipes/recipeAddForm";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
	public String doAddRecipe(Recipe recipe, @RequestParam("ingredients") String ingredients) {
		// List<Ingredient> ingredientsList =
		// ingredientService.splitIngredients(ingredients);
		List<RecipeIngredient> ingredientsList = new ArrayList<>();
		recipeService.createRecipe(recipe, ingredientsList);

		return "redirect:/listedRecipes";
	}

	@RequestMapping(value = "/doAddRecipe", method = RequestMethod.POST)
	public String doAddRecipe(Model model, HttpSession session, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("preparation") String preparation,
			@RequestParam("ingredients") List<Ingredient> ingredients) {
		AppUser user = (AppUser) session.getAttribute("user");
		String author = user.getUsername();
		Recipe recipe = new Recipe();
		recipeService.createRecipe(recipe, new ArrayList<RecipeIngredient>()); // FIXME:
																				// Tady
																				// uz
																				// se
																				// recept
																				// uklada
		// do DB, musi mit vyplneny hodnoty
		// (postup a ingredience)
		model.addAttribute("recipes", recipeService.getAllRecipesByUser(user));
		return "redirect:/listedRecipes";
	}

	@RequestMapping(value = "/doDeleteRecipe", method = RequestMethod.POST)
	public String deleteRecipe(Model model, HttpSession session, @RequestParam("recipe") Recipe recipe) {
		recipeService.deleteRecipe(recipe);
		AppUser user = (AppUser) session.getAttribute("user");
		model.addAttribute("recipes", recipeService.getAllRecipesByUser(user));
		return "listedRecipes";
	}

	@RequestMapping(value = "/search")
	public String showDishes(Model model) {
		model.addAttribute("allIngredients", ingredientService.getAll());
		model.addAttribute("reicpes", null);
		return "searchByIngredients";
	}

	@RequestMapping(value = "/searchBy")
	public String showDishes(Model model, @RequestParam("ingredients") String ingredientsString) {
		model.addAttribute("reicpes", null);
		return "searchByIngredients";
	}

	public String findRecipeByName(Model model) {

		return "listedRecipes";
	}
}
