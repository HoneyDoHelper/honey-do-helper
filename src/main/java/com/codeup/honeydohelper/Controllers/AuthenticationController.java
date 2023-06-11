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
        setCategoriesHtml(model);

        return "/authentication/login";
    }

    @PostMapping("/login")
    public String performLogin() {
        return "redirect:/dashboard"; // should redirect to home page or wherever after a successful login
    }


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
        setCategoriesHtml(model);

        model.addAttribute("user", new HoneyUsers());
        model.addAttribute("userProfile", new UserProfiles());

        return "/authentication/registerUser";
    }

    @PostMapping("/register/user")
    public String saveUser(@ModelAttribute HoneyUsers honeyUser,
                           @ModelAttribute UserProfiles userProfile,
                           @ModelAttribute Honeydoers honeydoer,
                           @RequestParam("image_url") String imageUrl,
                           @RequestParam("about_self") String aboutSelf){

        createHoneyUser(honeyUser);
        createUserProfile(userProfile, honeyUser, imageUrl);

        if (honeyUser.getIsHoneydoer()){
            createHoneydoer(honeydoer, aboutSelf);

            return "redirect:/register/honeydoer/" + findNewHoneydoer();
        } else {

            return "redirect:/index";
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

        return "/authentication/registerHoneydoer";
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
    private void createHoneyUser(HoneyUsers honeyUser){
        String hash = passwordEncoder.encode(honeyUser.getPassword());
        honeyUser.setPassword(hash);
        honeyUsersDao.save(honeyUser);
    }

    private void createHoneydoer(Honeydoers honeydoer, String aboutSelf){
        honeydoer.setUser(honeyUsersDao.findTopByOrderByIdDesc());
        honeydoer.setAboutSelf(aboutSelf);
        honeydoer.setRating(5);
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

}
