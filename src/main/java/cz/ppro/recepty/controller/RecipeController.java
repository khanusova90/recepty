package cz.ppro.recepty.controller;

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
import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.service.RecipeService;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
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
		// TODO vytvoøit tuto metodu
		// model.addAttribute("recipe", recipeService.getRecipeById(id));
		return "recipeDetail";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String addRecipe(Recipe recipe) {
		return "recipeAddForm";
	}

	@RequestMapping(value = "/doAddRecipe", method = RequestMethod.POST)
	public String doAddRecipe(Model model, HttpSession session, @RequestParam("name") String name,
							  @RequestParam("description") String description, @RequestParam("preparation") String preparation,
							  @RequestParam("ingredients") List<Ingredient> ingredients) {
		AppUser user = (AppUser) session.getAttribute("user");
		/*
		 * TODO insert correct constructor for all those params ratings bude v
		 * const pro toto 0 a 0
		 * 
		 * V Recipe je bezparametricky konstruktor. Nastavi hodnoceni a pocet
		 * hodnoticich na 0
		 */
		String author = user.getUsername();
		Recipe recipe = new Recipe();
		recipeService.createRecipe(recipe);
		model.addAttribute("recipes", recipeService.getAllRecipesByUserId(user.getIdAppUser()));
		return "redirect:/listedRecipes";
	}

	@RequestMapping(value = "/doDeleteRecipe", method = RequestMethod.POST)
	public String showDishes(Model model, HttpSession session, @RequestParam("recipe") Recipe recipe) {
		recipeService.deleteRecipe(recipe);
		AppUser user = (AppUser) session.getAttribute("user");
		model.addAttribute("recipes", recipeService.getAllRecipesByUserId(user.getIdAppUser()));
		return "listedRecipes";
	}
}
