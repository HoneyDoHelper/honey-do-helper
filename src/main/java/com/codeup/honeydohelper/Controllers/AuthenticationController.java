package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthenticationController {

    private final CategoriesRepository categoriesDao;
    private final HoneydoerServicesRepository honeydoerServicesDao;
    private final HoneydoersRepository honeydoersDao;
    private final ServicesRepository servicesDao;
    private final UserProfilesRepository userProfileDao;
    private final HoneyUsersRepository honeyUsersDao;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationController(
            CategoriesRepository categoriesDao,
            HoneydoerServicesRepository honeydoerServicesDao,
            HoneydoersRepository honeydoersDao,
            ServicesRepository servicesDao,
            UserProfilesRepository userProfileDao,
            HoneyUsersRepository honeyUsersDao,
            PasswordEncoder passwordEncoder
    ) {
        this.categoriesDao = categoriesDao;
        this.honeydoerServicesDao = honeydoerServicesDao;
        this.honeydoersDao = honeydoersDao;
        this.servicesDao = servicesDao;
        this.userProfileDao = userProfileDao;
        this.honeyUsersDao = honeyUsersDao;
        this.passwordEncoder = passwordEncoder;
    }

    /*/////////////////////////////////////////////////////////
    Login
    /////////////////////////////////////////////////////////*/
    @GetMapping("/login")
    public String gotoLogin(Model model){
        setCategoriesHtml(model);

        return "authentication/login";
    }


    /*/////////////////////////////////////////////////////////
    Register
    /////////////////////////////////////////////////////////*/
    @GetMapping("/register/user")
    public String showSignupForm(Model model){
        setCategoriesHtml(model);

        model.addAttribute("user", new HoneyUsers());
        model.addAttribute("userProfile", new UserProfiles());

        return "authentication/registerUser";
    }

    @PostMapping("/register/user")
    public String saveUser(@ModelAttribute HoneyUsers honeyUser,
                           @ModelAttribute UserProfiles userProfile,
                           @ModelAttribute Honeydoers honeydoer,
                           @RequestParam("password") String password,
                           @RequestParam("confirm_password") String confirmPassword,
                           @RequestParam("image_url") String imageUrl,
                           @RequestParam("about_self") String aboutSelf){

        if(!password.equals(confirmPassword)){
            return "redirect:/register/user?passwordsdontmatch";
        }

        boolean isStrongPassword = checkPasswordStrength(password);

        if(!isStrongPassword){
            return "redirect:/register/user?passwordnotstrong";
        }

        createHoneyUser(honeyUser, password);
        createUserProfile(userProfile, honeyUser, imageUrl);

        if (honeyUser.getIsHoneydoer()){
            createHoneydoer(honeydoer, aboutSelf);

            return "redirect:/register/honeydoer/" + findNewHoneydoer();
        } else {

            return "redirect:/dashboard";
        }
    }

    @GetMapping("/register/honeydoer/{newHoneyUserId}")
    public String gotoRegisterHoneydoer(Model model, @PathVariable int newHoneyUserId) {
        setCategoriesHtml(model);

        Honeydoers honeydoer = honeydoersDao.findByUser_Id(newHoneyUserId);
        model.addAttribute("newHoneydoer", honeydoer);

        setAllServicesHtml(model, honeydoer);
        setNewHoneyUserHtml(model);
        setHoneydoerServicesHtml(model, honeydoer);

        model.addAttribute("honeydoerServices", new HoneydoerServices());

        return "authentication/registerHoneydoer";
    }

    @PostMapping("/register/honeydoer/{newHoneydoerId}")
    public String submitForm(@ModelAttribute HoneydoerServices honeydoerServices,
                             @RequestParam("hourly-rate") String rate,
                             @RequestParam("service") int serviceId,
                             @RequestParam("honeydoerId") int honeyUserId) {

        Honeydoers honeydoer = findHoneydoer(honeyUserId);
        createHoneydoerService(honeydoerServices, rate, serviceId, honeydoer);

        return "redirect:/register/honeydoer/" + honeydoer.getUser().getId();
    }


    /*/////////////////////////////////////////////////////////
    ForgotPassword
    /////////////////////////////////////////////////////////*/
    @GetMapping("/passwordReset")
    public String gotoPasswordReset(Model model){
        setCategoriesHtml(model);

        return "authentication/passwordReset";
    }


    /*================================================================================
    Controller Methods to set model Attributes
    ================================================================================*/
    private HoneyUsers findLoggedInHoneyUser() {
        return (HoneyUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void setUserHtml(Model model, HoneyUsers honeyUser){
        model.addAttribute("user", honeyUser);
    }

    private void setCategoriesHtml(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);
    }

    private void setAllServicesHtml(Model model, Honeydoers honeydoer){
        List<Services> allServices = servicesDao.findAll();
        model.addAttribute("services", allServices);
    }

    private void setNewHoneyUserHtml(Model model){
        HoneyUsers newUser = honeyUsersDao.findTopByOrderByIdDesc();
        model.addAttribute("newUser", newUser);
    }

    private void setHoneydoerServicesHtml(Model model, Honeydoers honeydoer){
        List<HoneydoerServices> allHoneydoerServices = honeydoerServicesDao.findAllByHoneydoers_Id(honeydoer.getId());
        model.addAttribute("honeydoerService", allHoneydoerServices);
    }


    /*================================================================================
    Controller Methods to Create Honeyusers & Honeydoers
    ================================================================================*/
    private void createHoneyUser(HoneyUsers honeyUser, String password){
        String hash = passwordEncoder.encode(password);
        honeyUser.setPassword(hash);
        honeyUsersDao.save(honeyUser);
    }

    private void createHoneydoer(Honeydoers honeydoer, String aboutSelf){
        honeydoer.setUser(honeyUsersDao.findTopByOrderByIdDesc());
        honeydoer.setAboutSelf(aboutSelf);
        honeydoer.setRating(0);
        honeydoersDao.save(honeydoer);
    }

    private int findNewHoneydoer(){
        Honeydoers newHoneydoer = honeydoersDao.findTopByOrderByIdDesc();
        return newHoneydoer.getUser().getId();
    }

    private void createHoneydoerService(HoneydoerServices honeydoerServices,
                                        String rate, int serviceId, Honeydoers honeydoer ){
        honeydoerServices.setRate(Float.parseFloat(rate));

        Optional<Services> service = servicesDao.findById(serviceId);
        honeydoerServices.setServices(service.get());

        honeydoerServices.setHoneydoers(honeydoer);

        honeydoerServicesDao.save(honeydoerServices);
    }

    private void createUserProfile(UserProfiles userProfile, HoneyUsers honeyUser, String imageUrl){
        userProfile.setUser(honeyUser);
        userProfile.setImgFilePath(imageUrl);
        userProfileDao.save(userProfile);
    }

    private Honeydoers findHoneydoer(int honeyUserId){
        return honeydoersDao.findByUser_Id(honeyUserId);
    }

    private boolean checkPasswordStrength(String password) {
        boolean hasNumber = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSymbol = false;
        boolean isSixCharsOrMore = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSymbol = true;
            }
        }

        if (password.length() >= 6){
            isSixCharsOrMore = true;
        }

        return hasNumber && hasUpperCase && hasLowerCase && isSixCharsOrMore && hasSymbol;
    }

}