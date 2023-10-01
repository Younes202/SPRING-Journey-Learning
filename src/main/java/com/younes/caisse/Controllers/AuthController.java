package com.younes.caisse.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @GetMapping("/")
    public ModelAndView redirectToLogin() {
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public String login() {
        return "Auth/login"; // Return the logout template
    }

    @GetMapping("/logout")
    public String logout() {
        return "Auth/logout"; // Return the logout template
    }
}
