package cz.ppro.recepty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.ppro.recepty.domain.AppUser;

/**
 * LOGIN FUNCTIONALITY
 */

@Controller
class LoginController {

	@RequestMapping(value = "/user/login")
	public String showLoginForm(Model model) {
		AppUser user = new AppUser();
		model.addAttribute("newUser", user);
		return "user/login";
	}

	@RequestMapping(value = "/login")
	public String showLogin(Model model) {
		AppUser user = new AppUser();
		model.addAttribute("newUser", user);
		return "user/login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		AppUser user = new AppUser();
		model.addAttribute("newUser", user);
		model.addAttribute("loginError", true);
		return "user/login";
	}
}
