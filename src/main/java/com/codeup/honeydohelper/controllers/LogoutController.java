package com.codeup.honeydohelper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String performLogout() {
        return "redirect:/login?logout";
    }
}
// this needs a proper redirect to correct page after logout is complete