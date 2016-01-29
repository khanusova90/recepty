package cz.ppro.recepty.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.ppro.recepty.domain.AppUser;
import cz.ppro.recepty.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by tynak_000 on 26/01/2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {

       ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("error", "Nesprávné jméno nebo heslo");
        }
        if (logout != null) {
            model.addObject("msg", "Úspìšné pøihlášení.");
        }
        model.setViewName("login");

        return model;

    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(AppUser user) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid AppUser user, BindingResult br) {
        if (user == null || user.getUsername() == null) {
            return "redirect:/";
        }
        if (br.hasErrors()) {
            return "registration";
        }

        Boolean result = userService.saveUser(user);
        if (result) {
            return "homein";
        } else {
            return "registration";
        }
    }

    @RequestMapping(value = "/userdetail")
    public String showUserDetail(Principal principal) {
        return "user/userdetail";
    }

    @RequestMapping(value = "/logoutProcess")
    public String logout(HttpSession session) {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        session.removeAttribute("user");
        return "home";
    }

    @RequestMapping(value = "/userdetail")
    public String getUserDetails(Principal principal){
        return "user/userdetail";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        return null;

    }
}
