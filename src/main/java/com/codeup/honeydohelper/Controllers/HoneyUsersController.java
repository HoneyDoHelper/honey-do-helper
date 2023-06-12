package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;

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
        setCategoriesHtml(model);

        //Finds current logged in user and sets it in the html doc
        HoneyUsers currentLoggedInUser = findLoggedInHoneyUser();
        setUserHtml(model, currentLoggedInUser);

        //Puts user in appropriate dashboard based on isAdmin or isHoneydoer
        if (currentLoggedInUser.getIsAdmin()) {

            return "users/adminDashboard";
        } else if (currentLoggedInUser.getIsHoneydoer()) {
            setHoneydoerDashboardHtml(model, currentLoggedInUser.getId());
            setUserProfileHtml(model, currentLoggedInUser.getId());

            return "users/honeydoerDashboard";
        } else if (currentLoggedInUser != null){
            setUserProfileHtml(model, currentLoggedInUser.getId());

            return "users/userDashboard";
        } else {

            return "redirect:/login";
        }
    }

    @GetMapping("/edit/profile")
    public String editUserProfileForm(Model model){
        setCategoriesHtml(model);

        HoneyUsers currentLoggedInUser = findLoggedInHoneyUser();
        setUserHtml(model, currentLoggedInUser);

        setUserProfileHtml(model, currentLoggedInUser.getId());
        return "/users/editProfile";
    }

    @PostMapping("/edit/profile")
    public String editUserProfileSubmit(@RequestParam("honeyUserID") int honeyUserId,
                                        @RequestParam("userId") int userId,
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


        editHoneyUser(honeyUser, first_name, last_name);
        editUserProfile(userProfile, address, address2, city, state, zip, phone_number);

        return "redirect:/dashboard";
    }


    /*================================================================================
    Controller Methods to set model Attributes
    ================================================================================*/
    private HoneyUsers findLoggedInHoneyUser(){
        return (HoneyUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void setUserHtml(Model model, HoneyUsers honeyUser){
                model.addAttribute("user", honeyUser);
        UserProfiles userProfile = userProfileDao.findByUser_Id(honeyUser.getId());
        model.addAttribute("userProfile", userProfile);

    }

    private void setHoneydoerDashboardHtml(Model model, int honeyUserId){
        Honeydoers honeydoer = honeydoersDao.findByUser_Id(honeyUserId);
        model.addAttribute("honeydoer", honeydoer);
        setAllHoneydoerTasksHtml(model,honeydoer);
        setAllHoneydoerServicesHtml(model,honeydoer);
        setAllHoneydoerReviewsHtml(model,honeydoer);
    }


    private void setAllHoneydoerReviewsHtml(Model model, Honeydoers honeydoer){
        List<HoneydoerReviews> allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoer.getId());
        model.addAttribute("reviews", allReviews);
    }

    private void setAllHoneydoerServicesHtml(Model model, Honeydoers honeydoer){
        List<HoneydoerServices> allServices = honeydoerServicesDao.findAllByHoneydoers_Id(honeydoer.getId());
        model.addAttribute("services", allServices);
    }

    private void setAllHoneydoerTasksHtml(Model model, Honeydoers honeydoer){
        List<HoneydoerServices> allServices = honeydoerServicesDao.findAllByHoneydoers_Id(honeydoer.getId());
        List<Tasks> allTasks = new ArrayList<>();

        for (HoneydoerServices service: allServices) {
            List<Tasks> objects = tasksDao.findAllByHoneydoerService_Id(service.getId());
            allTasks.addAll(objects);
        }
        model.addAttribute("tasks", allTasks);
    }

    private void setCategoriesHtml(Model model){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);
    }

    private void setUserProfileHtml(Model model, int userId){
        UserProfiles userProfile = userProfileDao.findByUser_Id(userId);
        model.addAttribute("userProfile", userProfile);
    }

    /*================================================================================
    Controller Methods Edit Users
    ================================================================================*/
    private void editHoneyUser(HoneyUsers honeyUser, String first_name, String last_name){
        if (first_name != null){
            honeyUser.setFirstName(first_name);
        }
        if (last_name != null){
            honeyUser.setLastName(last_name);
        }

        honeyUsersDao.save(honeyUser);
    }

    private void editUserProfile(UserProfiles userProfile, String address, String address2,
                                 String city, String state, String zip, String phone_number){
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

        userProfileDao.save(userProfile);
    }


    /*================================================================================
    Controller Methods Delete Users
    ================================================================================*/

}
