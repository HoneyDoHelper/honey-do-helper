package com.codeup.honeydohelper.Controllers;
import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ServicesController {
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
    private final HoneyUsersRepository usersDao;
    public ServicesController (
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
            HoneyUsersRepository usersDao
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
        this.usersDao = usersDao;
    }
//    @GetMapping("/services")
//    public String listServices(Model model) {
//        model.addAttribute("posts", servicesDao.findAll());
//        return "services";
//    }
//    @GetMapping("/service/Categories")
//    public String showCategories(Model model) {
//        model.addAttribute("categories", categoriesDao.findAll());
//        return "serviceCategories";
//    }
//    @GetMapping("/services/Categories/{id}")
//    public String showExteriorServices(Model model){
//        model.addAttribute("exterior", categoriesDao.findById(1));
//        return "/services/serviceCategory";
//    }
//    @GetMapping("/services/Categories/{id}")
//    public String showInteriorServices(Model model){
//        model.addAttribute("interior", categoriesDao.findById(2));
//        return "/services/serviceCategory";
//    }
//    @GetMapping("/services/Categories/{id}")
//    public String showMiscellaneousServices(Model model){
//        model.addAttribute("miscellaneous", categoriesDao.findById(3));
//        return "/services/serviceCategory";
//    }
//    @GetMapping("/services/honeydoer/profile")
//    public String showHoneydoerProfile(Model model){
//        model.addAttribute("honeydoer", honeydoerServicesDao.findAll());
//        return "/services/honeydoerProfile";
//    }


    @GetMapping("/categories/{categoryId}")
    public String gotoCategory(Model model, @PathVariable int categoryId){

        Optional<Categories> category = categoriesDao.findById(categoryId);

        if(categoriesDao.findById(categoryId).isPresent()){
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
    public String gotoServices(Model model, @PathVariable int serviceId){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        Optional<Services> service = servicesDao.findById(serviceId);
        if(servicesDao.findById(serviceId).isPresent()){
            Services serviceObject = service.get();
            model.addAttribute("service", serviceObject);
        }

        List<HoneydoerServices> allHoneydoerServices = new ArrayList<>();
        allHoneydoerServices = honeydoerServicesDao.findAllByServices_Id(serviceId);
        model.addAttribute("honeydoerServices", allHoneydoerServices);

        return "/services/services";
    }


    @GetMapping("/categories")
    public String gotoCategories(Model model){

        List<Categories> allCategories = new ArrayList<>();
        allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);
        return "/services/serviceCategories";
    }


    @GetMapping("/services/honeydoer/{honeydoerId}/{serviceId}")
    public String gotoHoneydoerProfile(Model model, @PathVariable int honeydoerId, @PathVariable int serviceId){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);
        if(honeydoersDao.findById(honeydoerId).isPresent()){
            Honeydoers honeydoerObject = honeydoer.get();
            model.addAttribute("honeydoer", honeydoerObject);
        }

        Optional<HoneydoerServices> honeydoerService = honeydoerServicesDao.findById(serviceId);
        if(honeydoerServicesDao.findById(serviceId).isPresent()){
            HoneydoerServices honeydoerServiceObject = honeydoerService.get();
            model.addAttribute("service", honeydoerServiceObject);
        }

        List<HoneydoerReviews> allReviews = new ArrayList<>();
        allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoerId);
        model.addAttribute("reviews", allReviews);

        return "/services/honeydoerProfile";
    }

    @GetMapping("/services/bookService/{honeydoerId}/{serviceId}")
    public String gotoBookService(Model model, @PathVariable int honeydoerId, @PathVariable int serviceId){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);

        Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);
        if(honeydoersDao.findById(honeydoerId).isPresent()){
            Honeydoers honeydoerObject = honeydoer.get();
            model.addAttribute("honeydoer", honeydoerObject);
        }

        Optional<HoneydoerServices> honeydoerService = honeydoerServicesDao.findById(serviceId);
        if(honeydoerServicesDao.findById(serviceId).isPresent()){
            HoneydoerServices honeydoerServiceObject = honeydoerService.get();
            model.addAttribute("service", honeydoerServiceObject);
        }

        List<HoneydoerReviews> allReviews = new ArrayList<>();
        allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoerId);
        model.addAttribute("reviews", allReviews);

        model.addAttribute("newTask", new Tasks());


        return "/services/bookService";
    }
    @PostMapping("/services/bookService/{honeydoerId}/{serviceId}")
    public String submitProposal(@ModelAttribute Tasks tasks, @PathVariable int honeydoerId, @RequestParam("honeydoerServiceId") String honeydoerServiceId,
                                 @PathVariable int serviceId) {

//        tasks.getTaskDetails();
//        tasks.setDateAssigned(LocalDate.now());


        Optional<HoneydoerServices> honeydoer = honeydoerServicesDao.findById(Integer.parseInt(honeydoerServiceId));
        if (honeydoer.isPresent()) {
            HoneydoerServices honeydoerObject = honeydoer.get();
            tasks.setHoneydoerService(honeydoerObject);

        }

        Optional<HoneyUsers> user = usersDao.findById(2);
        if (user.isPresent()) {
            HoneyUsers userObject = user.get();
            tasks.setUser(userObject);

        }

        List<Tasks> allTasks = tasksDao.findAllByHoneydoerService_Id(serviceId);

//        tasks.setHoneydoerService(Integer.parseInt(honeydoerServiceId));
        tasks.setIsAccepted(false);
        tasks.setStatus("Pending");
        tasksDao.save(tasks);

        return "redirect:/about";
    }
}

//return "redirect:/register/honeydoer/" + userId + '/' + honeydoerServiceId;
//Optional<Tasks> newTasks = tasksDao.findById(userId);

