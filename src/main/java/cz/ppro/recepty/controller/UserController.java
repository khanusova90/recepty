package cz.ppro.recepty.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.ppro.recepty.service.UserService;

@Controller
@RequestMapping("/user")
class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userdetail")
	public String showUserDetail(Principal principal) {
		return "user/userdetail";
	}

}
