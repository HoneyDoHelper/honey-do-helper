package com.codeup.honeydohelper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password");
        }
        return "users/login"; // in case incorrect credentials are entered
    }

    @PostMapping("/login")
    public String performLogin() {
        return "redirect:/home"; // should redirect to home page or wherever after a successful login
    }

    // Other possible features

}