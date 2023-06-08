package com.codeup.honeydohelper.Controllers;
import com.codeup.honeydohelper.Models.Users;
import com.codeup.honeydohelper.Repositories.UsersRepository;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// register is incomplete more info needed , the code is from a previous project
@Controller
public class RegisterController {
    private UsersRepository userDao;
    private PasswordEncoder passwordEncoder;
    public RegisterController(UsersRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new Users());
        return "register";
    }
    @PostMapping("/register")
    public String saveUser(@ModelAttribute Users user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }
}