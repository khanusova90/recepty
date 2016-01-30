package cz.ppro.recepty.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.domain.Category;
import cz.ppro.recepty.domain.Ingredient;
import cz.ppro.recepty.domain.Photo;
import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.RecipeIngredient;
import cz.ppro.recepty.service.IngredientService;
import cz.ppro.recepty.service.RecipeService;
import cz.ppro.recepty.service.UserService;
import cz.ppro.recepty.utils.UserUtils;

@Controller
@RequestMapping("/recipes")
class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private UserService userService;

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
		RecipeIngredient recipeIngredient = new RecipeIngredient(recipe);
		recipeIngredient.setIdRecipeIngredient(-1l);
		recipe.getRecipeIngredients().add(recipeIngredient);
		model.addAttribute("recipe", recipe);
		model.addAttribute("categories", Category.values());
		model.addAttribute("recipeIngredients", new ArrayList<RecipeIngredient>());
		model.addAttribute("ingredients", ingredientService.getAll());
		model.addAttribute("photo", "fotka");
		model.addAttribute("ingredient", new Ingredient());

		return "recipes/recipeAddForm";
	}

	@RequestMapping(value = "/addRecipe", params = "addRecipeIngredient", method = RequestMethod.POST)
	public String addRow(final Recipe recipe, Model model, @ModelAttribute("ingredient") Ingredient ingredient) {
		RecipeIngredient recipeIngredient = new RecipeIngredient(recipe);
		// recipeIngredient.setIdRecipeIngredient(-1l);
		recipeIngredient.setIngredient(ingredient);
		recipe.getRecipeIngredients().add(recipeIngredient);
		model.addAttribute("recipe", recipe);
		model.addAttribute("categories", Category.values());
		model.addAttribute("recipeIngredients", new ArrayList<RecipeIngredient>());
		model.addAttribute("ingredients", ingredientService.getAll());
		model.addAttribute("photo", "fotka");
		return "recipes/recipeAddForm";
	}

	@RequestMapping(value = "/addRecipe/{recipeId}", params = "removeRecipeIngredient", method = RequestMethod.POST)
	public String deleteRow(final Recipe recipe, final HttpServletRequest request) {
		Long recipeIngredientId = Long.valueOf(request.getParameter("removeRecipeIngredient"));

		for (RecipeIngredient ri : recipe.getRecipeIngredients()) {
			if (ri.getIdRecipeIngredient().equals(recipeIngredientId)) {
				recipe.getRecipeIngredients().remove(ri);
				break;
			}
		}
		if (recipeIngredientId > 0) {
			recipeService.deleteRecipeIngredient(recipeIngredientId);
		}
		return "recipes/recipeAddForm";
	}

	@RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
	public String doAddRecipe(Recipe recipe, Model model) {
		// AppUser user = UserUtils.getActualUser();
		String username = UserUtils.getActualUsername();
		AppUser user = userService.findUserByUsername(username);

		recipeService.createRecipe(recipe, user);
		model.addAttribute("recipes", recipeService.getAllRecipesByUser(user));
		return "redirect:/listedRecipes";
	}

	@RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
	public String addPhoto(Recipe recipe, @RequestParam("ingredients") List<RecipeIngredient> ingredients,
			@RequestParam("myFile") MultipartFile file, Model model) {
		if (!file.isEmpty()) {
			String name = file.getName();
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(name + "-uploaded")));
				stream.write(bytes);
				stream.close();
				Photo photo = new Photo();
				photo.setPhoto(bytes);
				List<Photo> photos = null;
				photos.add(photo);
				recipe.setPhotos(photos);
				return "You successfully uploaded " + name + " into " + name + "-uploaded !";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "The selected file was empty and could not be uploaded.";
		}

		// AppUser user = UserUtils.getActualUser();
		// recipeService.createRecipe(recipe, ingredients, user);
		// model.addAttribute("recipes",
		// recipeService.getAllRecipesByUser(user));
		// return "redirect:/listedRecipes";
	}

	@RequestMapping(value = "/searchByIngredientAll")
	public String showRecipes(Model model, Locale locale) {
		model.addAttribute("recipes", null);
		model.addAttribute("selectedIngredients", messageSource.getMessage("insertIngredients", null, locale));
		return "searchByIngredients";
	}

	@RequestMapping(value = "/searchByIngredientAll", method = RequestMethod.POST)
	public String showRecipes(Model model, @ModelAttribute("selectedIngredients") String ingredientsString) {
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
