package com.codeup.honeydohelper.Controllers;

import ch.qos.logback.core.model.Model;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
// register is incomplete more info needed , the code is from a previous project
@Controller
public class RegisterController {
//    private UserRepository userDao;
//    private PasswordEncoder passwordEncoder;
//
//    public RegisterController(UserRepository userDao, PasswordEncoder passwordEncoder) {
//        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @GetMapping("/sign-up")
//    public String showSignupForm(Model model){
//        model.addAttribute("user", new User());
//        return "users/sign-up";
//    }
//
//    @PostMapping("/sign-up")
//    public String saveUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/login";
//    }
}
