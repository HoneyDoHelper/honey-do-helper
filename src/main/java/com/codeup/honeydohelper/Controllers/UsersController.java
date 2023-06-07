package com.codeup.honeydohelper.Controllers;
import com.codeup.honeydohelper.Models.Users;
import com.codeup.honeydohelper.Repositories.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class UsersController {
    private UsersRepository userDao;
    private PasswordEncoder passwordEncoder;
    public UsersController(UsersRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new Users());
        return "users/register";
    }
    @PostMapping("/register")
    public String saveUser(@ModelAttribute Users user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/index";
    }
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request) {
        Users currentLoggedInUser = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentLoggedInUser.getIsHoneydoer()) {
            return "redirect:/dashboard/honeydoer";
        } else if (currentLoggedInUser.getIsAdmin()) {
            return "redirect:/dashboard/admin";
        } else {
            return "redirect:/dashboard/user";
        }
    }
}
