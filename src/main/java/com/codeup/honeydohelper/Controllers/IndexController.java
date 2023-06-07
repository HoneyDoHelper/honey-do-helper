package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;
import com.codeup.honeydohelper.Models.*;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping("/register/honeydoer/{newHoneydoerId}")
    public String gotoRegisterHoneydoer(Model model, @PathVariable int newHoneydoerId) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        List<Services> allServices = new ArrayList<>();
        allServices = servicesDao.findAll();
        model.addAttribute("services", allServices);


        Users newUser = usersDao.findTopByOrderByIdDesc();
        model.addAttribute("newUser", newUser);


        Optional<Honeydoers> honeydoer = honeydoersDao.findById(newHoneydoerId);
        Honeydoers honeydoerObject = honeydoer.get();
        model.addAttribute("newHoneydoer", honeydoerObject);
        model.addAttribute("newHoneydoerId", newHoneydoerId);

        model.addAttribute("honeydoerServices", new HoneydoerServices());

        //If : logged in user available
        //Grab logged in user with Spring security principals)
        //honeydoerServicesDao/findByUser(User)


        return "/registerHoneydoer";
    }


//    @PostMapping("/submit")
//    public String submitForm(
//            @RequestParam("category_Id") int category_Id,
//            @RequestParam("service_Id") int service_Id,
//            @RequestParam("about_service") String about_service,
//            @RequestParam("rate") double rate, Model model) {
//        List<HoneydoerServices> entries = honeydoerServicesDao.findAll();
//        model.addAttribute("entries", entries);
//
//        return "redirect:/register/honeydoer";
//    }

    @PostMapping("/register/honeydoer/{newHoneydoerId}")
    public String submitForm(@ModelAttribute HoneydoerServices honeydoerServices) {
        honeydoerServicesDao.save(honeydoerServices);

        return "redirect:/about";
    }

    @GetMapping("register/user")
    public String gotoRegisterUser(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        return "/registerUser";
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
        return "/services/serviceCategorties";
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
}
