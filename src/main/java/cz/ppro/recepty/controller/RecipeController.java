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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showRecipes(Model model){
		model.addAttribute("recipes", recipeService.getAllRecipes());
		return "listedRecipes";
	}

	@RequestMapping(value = "/{recipeType}")
	public void showRecipesByCategory(HttpSession session, Model model, @PathVariable("recipeType") String recipeType) {

	}

	@RequestMapping(value = "/detail")
	public String showRecipesDetail(Recipe recipe, Model model,HttpSession session) {
		model.addAttribute("recipeIngredients", recipeService.getAllIngredients(recipe.getIdRecipe()));
		//model.addAttribute("recipe", recipeService.ge);
		return "recipeDetail";
	}



	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String addRecipe(Recipe recipe) {
		return "recipeAddForm";
	}

//	@RequestMapping(value = "/doAddRecipe", method = RequestMethod.POST)
//public Stirng addRecipe(@RequestParam)


	@RequestMapping(value = "/doDeleteRecipe", method = RequestMethod.POST)
	public String showDishes(@RequestParam Long id){
		recipeService.deleteRecipe(id);
		return "listedRecipes";
	}




}
