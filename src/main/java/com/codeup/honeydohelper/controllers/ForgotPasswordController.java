package com.codeup.honeydohelper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPasswordController {

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "users/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String initiatePasswordReset(@RequestParam("email") String email, Model model) {
        String resetToken = generatePasswordResetToken();
        sendPasswordResetEmail(email, resetToken);
        model.addAttribute("emailSent", true);
        return "users/forgot-password"; // to send an email, with reset token
    }

    private void sendPasswordResetEmail(String email, String resetToken) {
    }

    private String generatePasswordResetToken() {
        return null;
    }
}