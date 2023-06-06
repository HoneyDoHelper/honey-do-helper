package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.UserDetails;
import com.codeup.honeydohelper.Models.Users;
import com.codeup.honeydohelper.Repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserDetailsRepository userDetailsDao;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());
        return "registration-form";
    }

//    @PostMapping
//    public String processRegistrationForm(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult, @RequestParam("imageFile") MultipartFile imageFile) {
//        if (bindingResult.hasErrors()) {
//            return "registration-form";
//        }
//
//        if (!user.getPassword().equals(user.getConfirmPassword())) {
//            bindingResult.rejectValue("confirmPassword", "error.user", "Passwords do not match");
//            return "registration-form";
//        }
//
//        if (!isPasswordStrong(user.getPassword())) {
//            bindingResult.rejectValue("password", "error.user", "Password must contain at least 1 number, 1 special character, and 1 uppercase letter");
//            return "registration-form";
//        }
//
//        Users savedUser = userDetailsDao.save(User);
//
//        UserDetails userDetails = new UserDetails();
//        userDetails.setAddress(user.getAddress());
//        userDetails.setAddress2(user.getAddress2());
//        userDetails.setCity(user.getCity());
//        userDetails.setState(user.getState());
//        userDetails.setZip(user.getZip());
//        userDetails.setPhone(user.getPhone());
//        userDetails.setImgFilePath("default/image/path.jpg");
//        userDetails.setUser(savedUser);
//
//        userDetailsRepository.save(userDetails);
//
//        if (!imageFile.isEmpty()) {
//        }
//
//        return "registration-success";
//    }
    // save the commented out code we can use my custom code elsewhere in the UsersController

    private boolean isPasswordStrong(String password) {
        boolean hasNumber = false;
        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (isSpecialCharacter(ch)) {
                hasSpecialChar = true;
            }
        }

        return hasNumber && hasLowercase && hasUppercase && hasSpecialChar && password.length() >= 8;
    }

    private boolean isSpecialCharacter(char ch) {

        String specialChars = "!@#$%^&*+="; // list of special characters
        return specialChars.contains(String.valueOf(ch));
    }
}
