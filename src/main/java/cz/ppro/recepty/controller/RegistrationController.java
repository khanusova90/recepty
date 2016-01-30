package cz.ppro.recepty.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.service.UserService;

@Controller
class RegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("newUser") AppUser user, Model model, Locale locale) {
		Boolean success = userService.saveUser(user);
		if (success) {
			model.addAttribute("message", messageSource.getMessage("login.user.added", null, locale));
			return "redirect:home";
		} else {
			model.addAttribute("errMsg", messageSource.getMessage("validation.login.exists", null, locale));
			return "user/login";
		}
	}
}