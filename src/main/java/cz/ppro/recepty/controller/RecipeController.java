package cz.ppro.recepty.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.service.RecipeService;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showRecipes(Model model, HttpSession session) {
		return "listedRecipes";
	}

	@RequestMapping(value = "/recipes/{recipeType}")
	public void showRecipesByCategory(HttpSession session, Model model, @PathVariable("recipeType") String recipeType) {

	}

	@RequestMapping(value = "/recipe/detail")
	public String showRecipesDetail(Recipe recipe, Model model,HttpSession session) {
		model.addAttribute("recipeIngredients", recipeService.getAllIngredients(recipe.getIdRecipe()));
		//model.addAttribute("recipe", recipeService.ge);
		return "recipeDetail";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addRecipe(Recipe recipe, BindingResult br) {
		if (recipe == null) {
			return "redirect:/";
		}
		if (br.hasErrors()) {
			return "add";
		}
		recipeService.createRecipe(recipe);
		return null;
	}
	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public String showDishes(Model model){
		model.addAttribute("allRecipes", recipeService.getAllRecipes());
		return "listedRecipes";
	}
}
