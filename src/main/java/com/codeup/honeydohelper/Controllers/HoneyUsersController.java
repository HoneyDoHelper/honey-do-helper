package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HoneyUsersController {

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


    public HoneyUsersController(
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

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        HoneyUsers currentLoggedInUser = (HoneyUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("current first name "+ currentLoggedInUser.getFirstName());
        System.out.println("current id "+ currentLoggedInUser.getId());
        model.addAttribute("user", currentLoggedInUser);

        Honeydoers honeydoer = honeydoersDao.findByUser_Id(currentLoggedInUser.getId());
        model.addAttribute("honeydoer", honeydoer);

        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

//        Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);
//        if(honeydoersDao.findById(honeydoerId).isPresent()){
//            Honeydoers honeydoerObject = honeydoer.get();
//            model.addAttribute("honeydoer", honeydoerObject);
//        }

        List<HoneydoerServices> allServices = new ArrayList<>();
        allServices = honeydoerServicesDao.findAllByHoneydoers_Id(honeydoer.getId());
        model.addAttribute("services", allServices);


        List<Tasks> allTasks = new ArrayList<>();
        for (HoneydoerServices service: allServices) {
            List<Tasks> objects = new ArrayList<>();
            System.out.println("service = " + service.getId());
            objects = tasksDao.findAllByHoneydoerService_Id(service.getId());

            allTasks.addAll(objects);
        }
        model.addAttribute("tasks", allTasks);

        List<HoneydoerReviews> allReviews = new ArrayList<>();
        allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoer.getId());
        model.addAttribute("reviews", allReviews);

        if (currentLoggedInUser.getIsHoneydoer()) {
            return "users/honeydoerDashboard";
        } else if (currentLoggedInUser.getIsAdmin()) {

            return "users/adminDashboard";
        } else {
            return "users/userDashboard";
        }
    }


    @GetMapping("/user/honeydoer/dashboard/{honeydoerId}")
    public String gotoHoneydoerDashboard(Model model, @PathVariable int honeydoerId){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);
        if(honeydoersDao.findById(honeydoerId).isPresent()){
            Honeydoers honeydoerObject = honeydoer.get();
            model.addAttribute("honeydoer", honeydoerObject);
        }

        List<HoneydoerServices> allServices = new ArrayList<>();
        allServices = honeydoerServicesDao.findAllByHoneydoers_Id(honeydoerId);
        model.addAttribute("services", allServices);


        List<Tasks> allTasks = new ArrayList<>();
        for (HoneydoerServices service: allServices) {
            List<Tasks> objects = new ArrayList<>();
            System.out.println("service = " + service.getId());
            objects = tasksDao.findAllByHoneydoerService_Id(service.getId());

            allTasks.addAll(objects);
        }
        model.addAttribute("tasks", allTasks);

        List<HoneydoerReviews> allReviews = new ArrayList<>();
        allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoerId);
        model.addAttribute("reviews", allReviews);

        return "/users/honeydoerDashboard";
    }

    @GetMapping("/edit/profile/{userId}")
    public String editUserProfileForm(Model model, @PathVariable int userId){
        UserProfiles userProfile = userProfileDao.findByUser_Id(userId);
        model.addAttribute("userProfile", userProfile);

        return "/users/editProfile";
    }

    @PostMapping("/edit/profile/{userId}")
    public String editUserProfileSubmit(@RequestParam("honeyUserID") int honeyUserId,
                                        @RequestParam("address") String address,
                                        @RequestParam("address2") String address2,
                                        @RequestParam("city") String city,
                                        @RequestParam("state") String state,
                                        @RequestParam("zip") String zip,
                                        @RequestParam("first_name") String first_name,
                                        @RequestParam("last_name") String last_name,
                                        @RequestParam("phone_number") String phone_number

    ){
        Optional<HoneyUsers> honeyUsersOptional = honeyUsersDao.findById(honeyUserId);
        HoneyUsers honeyUser = honeyUsersOptional.get();
        UserProfiles userProfile = userProfileDao.findByUser_Id(honeyUserId);


        if (address != null){
            userProfile.setAddress(address);
        }
        if (address2 != null){
            userProfile.setAddress2(address2);
        }
        if (city != null){
            userProfile.setCity(city);
        }
        if (state != null){
            userProfile.setState(state);
        }
        if (zip != null){
            userProfile.setZip(Integer.parseInt(zip));
        }
        if (phone_number != null){
            userProfile.setPhone(Long.parseLong(phone_number));
        }
        if (first_name != null){
            honeyUser.setFirstName(first_name);
        }
        if (last_name != null){
            honeyUser.setLastName(last_name);
        }


        userProfileDao.save(userProfile);
        honeyUsersDao.save(honeyUser);

        return "redirect:/index";
    }
}
