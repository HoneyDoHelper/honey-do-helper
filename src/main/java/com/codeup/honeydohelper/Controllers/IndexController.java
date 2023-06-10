package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Optional;
import com.codeup.honeydohelper.Models.Honeydoers;
import java.util.List;
import java.util.ArrayList;
import com.codeup.honeydohelper.Models.HoneydoerServices;
import com.codeup.honeydohelper.Models.Tasks;
import com.codeup.honeydohelper.Models.HoneydoerReviews;




import java.util.List;

@Controller
public class IndexController {

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

    public IndexController (
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
            HoneyUsersRepository honeyUsersDao
    ){
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
    }


    /*/////////////////////////////////////////////////////////
    /Index  |  /Contact  |  /About  |  /Support
    /////////////////////////////////////////////////////////*/

    @GetMapping("/index")
    public String gotoIndex(Model model) {
        displayServiceCategoriesForNav(model);

        return "/index";
    }

    @GetMapping("/contact")
    public String gotoContact(Model model){
        displayServiceCategoriesForNav(model);

        return "/contact";
    }

    @GetMapping("/about")
    public String gotoAbout(Model model) {
        displayServiceCategoriesForNav(model);

        return "/about";
    }

    @GetMapping("/support")
    public String gotoSupport(Model model){
        displayServiceCategoriesForNav(model);

        return "/support";
    }


    private void displayServiceCategoriesForNav(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);
    }
}
