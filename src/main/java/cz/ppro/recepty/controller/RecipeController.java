package cz.ppro.recepty.controller;

import cz.ppro.recepty.domain.Recipe;
import cz.ppro.recepty.domain.User;
import cz.ppro.recepty.service.RecipeService;
import cz.ppro.recepty.service.impl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;


    @RequestMapping(value = "/recipe", method = RequestMethod.GET)
    public String showRecipes(Model model, HttpSession session){
        return "start";
    }

    @RequestMapping(value = "/recipe/{recipeType}")
    public void showRecipesByCategory(HttpSession session, Model model, @PathVariable("recipeType") String recipeType) {

    }

    @RequestMapping(value = "/recipe/detail")
    public String showRecipesDetail(int recipeId) {
    return "/recipe/detail" + recipeId;
    }

    @RequestMapping(value = "/recipe/add", method = RequestMethod.POST)
    public String registerUser(Recipe recipe, BindingResult br) {
        if (recipe == null) {
            return "redirect:/";
        }
        if (br.hasErrors()) {
            return "add";
        }
        recipe.setRating(0);
        recipe.setRateCount(0);
        recipeService.createRecipe(recipe);

        return null;
    }

}
