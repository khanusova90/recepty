package cz.ppro.recepty.controller;

import cz.ppro.recepty.service.RecipeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class StartController {

	@Autowired
	RecipeService recipeService;

	private static final Log logger = LogFactory.getLog(StartController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start(Model model) {
		logger.info("App started");
		model.addAttribute("recipes",recipeService.getRecipesSortedByRating());
		return "home";

	}

	@RequestMapping(value = "/home")
	public String getHomePage(Model model) {
		model.addAttribute("recipes",recipeService.getRecipesSortedByRating());
		return "home";
	}
}
