package cz.ppro.recepty.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.ppro.recepty.service.UserService;

/**
 * Created by tynak_000 on 26/01/2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/singin", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "user/singin";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("newUser") AppUser user, BindingResult br) {
		if (user == null) {
			return "redirect:/homein";
		}
		if (br.hasErrors()) {
			return "user/signup";
		}
		Boolean success = userService.saveUser(user);

		if (success) {
			return "homein";
		} else {
			return "user/signup";
		}
	}

	@RequestMapping(value = "/userdetail")
	public String showUserDetail(Principal principal) {
		return "user/userdetail";
	}

	// @RequestMapping(value = "/logout")
	// public String logout(HttpSession session) {
	// SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
	// session.removeAttribute("user");
	// return "redirect:/";
	// }
}
