package cz.ppro.recepty.controller;

import cz.ppro.recepty.service.UserService;
import cz.ppro.recepty.service.impl.UserServiceImpl;
import cz.ppro.recepty.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by tynak_000 on 26/01/2016.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/user/singin", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "user/singin";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public String registerUser(User user, BindingResult br) {
        if (user == null) {
            return "redirect:/homein";
        }
        if (br.hasErrors()) {
            return "user/singup";
        }
        user.setRole("USER");
        userService.createUser(user);

        return "homein";
    }

    @RequestMapping(value = "/user/userdetail")
    public String showUserDetail(Principal principal) {
        return "user/userdetail";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        session.removeAttribute("user");
        return "redirect:/";
    }
}
