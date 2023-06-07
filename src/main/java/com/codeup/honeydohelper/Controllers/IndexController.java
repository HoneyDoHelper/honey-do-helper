package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;
import com.codeup.honeydohelper.Models.*;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final UserDetailsRepository userDetailsDao;
    private final UsersRepository usersDao;

    public IndexController(
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
            UserDetailsRepository userDetailsDao,
            UsersRepository usersDao
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
        this.userDetailsDao = userDetailsDao;
        this.usersDao = usersDao;
    }


    /*/////////////////////////////////////////////////////////
    Index
    /////////////////////////////////////////////////////////*/
    @GetMapping("/")
    public String gotoIndex(Model model) {
        List<Categories> allCategories = new ArrayList<>();
        allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/index";
    }

    @GetMapping("/login")
    public String gotoLogin(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/login";
    }

    @GetMapping("/contact")
    public String gotoContact(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);


        return "/contact";
    }

    @GetMapping("/about")
    public String gotoAbout(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/about";
    }

    @GetMapping("/passwordReset")
    public String gotoPasswordReset(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/passwordReset";
    }

    @GetMapping("/register")
    public String gotoRegister(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/register";
    }

    @GetMapping("/register/honeydoer")
    public String gotoRegisterHoneydoer() {
        return "/registerHoneydoer";
    }

    @GetMapping("/support")
    public String gotoSupport(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/support";
    }

    /*/////////////////////////////////////////////////////////
    Services
    /////////////////////////////////////////////////////////*/

    @GetMapping("/categories/{categoryId}")
    public String gotoCategory(Model model, @PathVariable int categoryId) {

        Optional<Categories> category = categoriesDao.findById(categoryId);

        if (categoriesDao.findById(categoryId).isPresent()) {
            Categories categoryObject = category.get();
            model.addAttribute("category", categoryObject);
        }

        List<Services> allServices = new ArrayList<>();
        allServices = servicesDao.findAllByCategory_Id(categoryId);
        model.addAttribute("services", allServices);

        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/services/serviceCategory";
    }


    @GetMapping("/services/{serviceId}")
    public String gotoServices(Model model, @PathVariable int serviceId) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        Optional<Services> service = servicesDao.findById(serviceId);
        if (servicesDao.findById(serviceId).isPresent()) {
            Services serviceObject = service.get();
            model.addAttribute("service", serviceObject);
        }

        List<HoneydoerServices> allHoneydoerServices = new ArrayList<>();
        allHoneydoerServices = honeydoerServicesDao.findAllByServices_Id(serviceId);
        model.addAttribute("honeydoerServices", allHoneydoerServices);

        return "/services/services";
    }


    @GetMapping("/categories")
    public String gotoCategories(Model model) {

        List<Categories> allCategories = new ArrayList<>();
        allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/services/serviceCategories";
    }


    @GetMapping("/services/honeydoer/{honeydoerId}/{serviceId}")
    public String gotoHoneydoerProfile(Model model, @PathVariable int honeydoerId, @PathVariable int serviceId) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);
        if (honeydoersDao.findById(honeydoerId).isPresent()) {
            Honeydoers honeydoerObject = honeydoer.get();
            model.addAttribute("honeydoer", honeydoerObject);
        }

        Optional<HoneydoerServices> honeydoerService = honeydoerServicesDao.findById(serviceId);
        if (honeydoerServicesDao.findById(serviceId).isPresent()) {
            HoneydoerServices honeydoerServiceObject = honeydoerService.get();
            model.addAttribute("service", honeydoerServiceObject);
        }

        List<HoneydoerReviews> allReviews = new ArrayList<>();
        allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoerId);
        model.addAttribute("reviews", allReviews);

        return "/services/honeydoerProfile";
    }

    @GetMapping("/user/honeydoer/dashboard/{honeydoerId}")
    public String gotoHoneydoerDashboard(Model model, @PathVariable int honeydoerId) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);
        if (honeydoersDao.findById(honeydoerId).isPresent()) {
            Honeydoers honeydoerObject = honeydoer.get();
            model.addAttribute("honeydoer", honeydoerObject);
        }

        List<HoneydoerServices> allServices = new ArrayList<>();
        allServices = honeydoerServicesDao.findAllByHoneydoers_Id(honeydoerId);
        model.addAttribute("services", allServices);


        List<Tasks> allTasks = new ArrayList<>();
        for (HoneydoerServices service : allServices) {
            List<Tasks> objects = new ArrayList<>();
            objects = tasksDao.findAllByHoneydoerService_Id(service.getId());

            allTasks.addAll(objects);
        }
        model.addAttribute("tasks", allTasks);


        List<HoneydoerReviews> allReviews = new ArrayList<>();
        allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoerId);
        model.addAttribute("reviews", allReviews);

        return "/users/honeydoerDashboard";
    }

    @GetMapping("/services/bookService/{honeydoerId}/{serviceId}")
    public String gotoHoneydoerDashboard(Model model, @PathVariable int honeydoerId, @PathVariable int serviceId){
        displayServiceCategoriesForNav(model);

        Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);
        if (honeydoersDao.findById(honeydoerId).isPresent()) {
            Honeydoers honeydoerObject = honeydoer.get();
            model.addAttribute("honeydoer", honeydoerObject);
        }

        Optional<HoneydoerServices> honeydoerService = honeydoerServicesDao.findById(serviceId);
        if (honeydoerServicesDao.findById(serviceId).isPresent()) {
            HoneydoerServices honeydoerServiceObject = honeydoerService.get();
            model.addAttribute("service", honeydoerServiceObject);
        }

        List<HoneydoerReviews> allReviews = new ArrayList<>();
        allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoerId);
        model.addAttribute("reviews", allReviews);

        return "/services/bookService";
    }

    private void displayServiceCategoriesForNav(Model model){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);
    }

}
