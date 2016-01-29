package cz.ppro.recepty.controller;

import javax.servlet.http.HttpSession;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.repository.RecipeRepository;
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

import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeRepository recipeRepo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showRecipes(Model model){
		model.addAttribute("recipes", recipeService.getAllRecipes());
		return "listedRecipes";
	}

	@RequestMapping(value = "/{recipeType}")
	public void showRecipesByCategory(HttpSession session, Model model, @PathVariable("recipeType") String recipeType) {
		model.addAttribute("ingredients", recipeService.showRecipesByCategory(recipeType));
	}

	@RequestMapping(value = "/detail")
	public String showRecipesDetail(Model model,HttpSession session,@RequestParam("id") Long id) {
		model.addAttribute("recipeIngredients", recipeService.getAllIngredients(id));
		model.addAttribute("recipe", recipeService.getRecipeById(id));
		return "recipeDetail";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String addRecipe(Recipe recipe) {
		return "recipeAddForm";
	}

	@RequestMapping(value = "/doAddRecipe", method = RequestMethod.POST)
    public String doAddRecipe(@RequestParam("name") String name,
							@RequestParam("description") String description,
							@RequestParam("preparation") String preparation,
							@RequestParam("ingredients") List<Ingredient> ingredients
							){
		AppUser user = (AppUser)session.getAttribute("user");
		Recipe recipe = new Recipe(name,description,preparation,ingredients);

		recipeService.createRecipe(Re);
	}

	@RequestMapping(value = "/doDeleteRecipe", method = RequestMethod.POST)
	public String showDishes(Model model,HttpSession session,@RequestParam("id") Long id){
		recipeService.deleteRecipe(id);
		AppUser user = (AppUser)session.getAttribute("user");
		model.addAttribute("recipes", recipeService.getAllRecipesByUserId(user.getIdAppUser()));
		return "listedRecipes";
	}




}
