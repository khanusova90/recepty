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
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showRecipes(Model model, HttpSession session) {
		return "start";
	}

	@RequestMapping(value = "/{recipeType}")
	public void showRecipesByCategory(HttpSession session, Model model, @PathVariable("recipeType") String recipeType) {

	}

	@RequestMapping(value = "/detail")
	public String showRecipesDetail(int recipeId) {
		return "/recipe/detail" + recipeId;
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

}
