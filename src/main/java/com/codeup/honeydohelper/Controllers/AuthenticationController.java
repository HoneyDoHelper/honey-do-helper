package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthenticationController {

    private final CategoriesRepository categoriesDao;
    private final ChatsRepository chatsDao;
    private final ClientReviewsRepository clientReviewsDao;
    private final HoneydoerImagesRepository honeydoerImagesDao;
    private final HoneydoerReviewsRepository honeydoerReviewsDao;
    private final HoneydoerSchedulesRepository honeydoerSchedulesDao;
    private final HoneydoerServicesRepository honeydoerServicesDao;
    private final HoneydoersRepository honeydoersDao;
    private final ServicesRepository servicesDao;
    private final TaskCostsRepository tasksCostsDao;
    private final TasksRepository tasksDao;
    private final TimeBlocksRepository timeBlocksDao;
    private final UserProfilesRepository userProfileDao;
    private final HoneyUsersRepository honeyUsersDao;
    private PasswordEncoder passwordEncoder;


    public AuthenticationController(
            CategoriesRepository categoriesDao,
            ChatsRepository chatsDao,
            ClientReviewsRepository clientReviewsDao,
            HoneydoerImagesRepository honeydoerImagesDao,
            HoneydoerReviewsRepository honeydoerReviewsDao,
            HoneydoerSchedulesRepository honeydoerSchedulesDao,
            HoneydoerServicesRepository honeydoerServicesDao,
            HoneydoersRepository honeydoersDao,
            ServicesRepository servicesDao,
            TaskCostsRepository tasksCostsDao,
            TasksRepository tasksDao,
            TimeBlocksRepository timeBlocksDao,
            UserProfilesRepository userProfileDao,
            HoneyUsersRepository honeyUsersDao,
            PasswordEncoder passwordEncoder
    ) {
        this.categoriesDao = categoriesDao;
        this.chatsDao = chatsDao;
        this.clientReviewsDao = clientReviewsDao;
        this.honeydoerImagesDao = honeydoerImagesDao;
        this.honeydoerReviewsDao = honeydoerReviewsDao;
        this.honeydoerSchedulesDao = honeydoerSchedulesDao;
        this.honeydoerServicesDao = honeydoerServicesDao;
        this.honeydoersDao = honeydoersDao;
        this.servicesDao = servicesDao;
        this.tasksCostsDao = tasksCostsDao;
        this.tasksDao = tasksDao;
        this.timeBlocksDao = timeBlocksDao;
        this.userProfileDao = userProfileDao;
        this.honeyUsersDao = honeyUsersDao;
        this.passwordEncoder = passwordEncoder;
    }

    /*/////////////////////////////////////////////////////////
    Login
    /////////////////////////////////////////////////////////*/
    @GetMapping("/login")
    public String gotoLogin(Model model){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/authentication/login";
    }

//    @GetMapping("/login")
//    public String showLoginForm(@RequestParam(name = "error", required = false) String error, Model model) {
//        if (error != null) {
//            model.addAttribute("errorMessage", "Invalid username or password");
//        }
//        return "/login"; // in case incorrect credentials are entered
//    }
//    @PostMapping("/login")
//    public String performLogin() {
//        return "redirect:/home"; // should redirect to home page or wherever after a successful login
//    }


    /*/////////////////////////////////////////////////////////
    Logout
    /////////////////////////////////////////////////////////*/
//    @GetMapping("/logout")
//    public String performLogout() {
//        return "redirect:/login?logout";
//    }

    //this needs a proper redirect to correct page after logout is complete


    /*/////////////////////////////////////////////////////////
    Register
    /////////////////////////////////////////////////////////*/
    @GetMapping("/register/user")
    public String showSignupForm(Model model){
        model.addAttribute("user", new HoneyUsers());
        model.addAttribute("userProfile", new UserProfiles());
        return "/authentication/registerUser";
    }

    @PostMapping("/register/user")
    public String saveUser(@ModelAttribute HoneyUsers honeyUser, @ModelAttribute UserProfiles userProfile, @ModelAttribute Honeydoers honeydoer,
                           @RequestParam("about_self") String aboutSelf){
        String hash = passwordEncoder.encode(honeyUser.getPassword());
        honeyUser.setPassword(hash);
        honeyUsersDao.save(honeyUser);

        userProfile.setUser(honeyUser);
        userProfileDao.save(userProfile);

        if (honeyUser.getIsHoneydoer()){
            honeydoer.setUser(honeyUsersDao.findTopByOrderByIdDesc());
            honeydoer.setAboutSelf(aboutSelf);
            honeydoer.setRating(5);
            honeydoersDao.save(honeydoer);
            Honeydoers newHoneydoer = honeydoersDao.findTopByOrderByIdDesc();
            int newHoneydoerId = newHoneydoer.getUser().getId();
            System.out.println("newHoneyUserId = " + newHoneydoerId);
            return "redirect:/register/honeydoer/" + newHoneydoerId;
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/register/honeydoer/{newHoneyUserId}")
    public String gotoRegisterHoneydoer(Model model, @PathVariable int newHoneyUserId) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        List<Services> allServices = new ArrayList<>();
        allServices = servicesDao.findAll();
        model.addAttribute("services", allServices);


        HoneyUsers newUser = honeyUsersDao.findTopByOrderByIdDesc();
        model.addAttribute("newUser", newUser);


        Honeydoers honeydoer = honeydoersDao.findByUser_Id(newHoneyUserId);

        System.out.println("honeydoerId GET = " + honeydoer.getId());

        model.addAttribute("newHoneydoer", honeydoer);
        model.addAttribute("newHoneydoerId", honeydoer.getId());

        List<HoneydoerServices> allHoneydoerServices = new ArrayList<>();
        allHoneydoerServices = honeydoerServicesDao.findAllByHoneydoers_Id(newHoneydoerId);
        model.addAttribute("honeydoerService", allHoneydoerServices);



        model.addAttribute("honeydoerServices", new HoneydoerServices());

        return "/authentication/registerHoneydoer";
    }

    @PostMapping("/register/honeydoer/{newHoneydoerId}")
    public String submitForm(@ModelAttribute HoneydoerServices honeydoerServices, @RequestParam("hourly-rate") String rate,
                             @RequestParam("service") int serviceId, @RequestParam("honeydoerId") int honeyUserId) {

        honeydoerServices.setRate(Float.parseFloat(rate));

        Optional<Services> service = servicesDao.findById(serviceId);
        honeydoerServices.setServices(service.get());

        //Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);
        //honeydoerServices.setHoneydoers(honeydoer.get());

        Honeydoers honeydoer = honeydoersDao.findByUser_Id(honeyUserId);
        honeydoerServices.setHoneydoers(honeydoer);

        honeydoerServicesDao.save(honeydoerServices);

        return "redirect:/register/honeydoer/" + honeydoer.getUser().getId();
    }


    /*/////////////////////////////////////////////////////////
    ForgotPassword
    /////////////////////////////////////////////////////////*/
    @GetMapping("/passwordReset")
    public String gotoPasswordReset(Model model){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/authentication/passwordReset";
    }

//    @GetMapping("/forgot-password")
//    public String showForgotPasswordForm() {
//        return "users/forgot-password";
//    }
//
//    @PostMapping("/forgot-password")
//    public String initiatePasswordReset(@RequestParam("email") String email, Model model) {
//        String resetToken = generatePasswordResetToken();
//        sendPasswordResetEmail(email, resetToken);
//        model.addAttribute("emailSent", true);
//        return "users/forgot-password"; // to send an email, with reset token
//    }
//
//    private void sendPasswordResetEmail(String email, String resetToken) {
//    }
//
//    private String generatePasswordResetToken() {
//        return null;
//    }
}
