package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

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
            UserDetailsRepository userDetailsDao,
            UsersRepository usersDao
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
        this.userDetailsDao = userDetailsDao;
        this.usersDao = usersDao;
    }


    /*/////////////////////////////////////////////////////////
    Index
    /////////////////////////////////////////////////////////*/
    @GetMapping("/")
    public String gotoIndex(){

        return "/index";
    }

    @GetMapping("/login")
    public String gotoLogin(){

        return "/login";
    }

    @GetMapping("/contact")
    public String gotoContact(){

        return "/contact";
    }

    @GetMapping("/about")
    public String gotoAbout(){

        return "/about";
    }

    @GetMapping("/passwordReset")
    public String gotoPasswordReset(){

        return "/passwordReset";
    }

    @GetMapping("/register")
    public String gotoRegister(){

        return "/register";
    }

    @GetMapping("/support")
    public String gotoSupport(){

        return "/support";
    }

    /*/////////////////////////////////////////////////////////
    Services
    /////////////////////////////////////////////////////////*/
    @GetMapping("/services/services")
    public String gotoServices(){

        return "/services/services";
    }

    @GetMapping("/services/exterior")
    public String gotoExteriorServices(){

        return "/services/serviceCategory";
    }

    @GetMapping("/services/interior")
    public String gotoInteriorServices(){

        return "/services/serviceCategory";
    }

    @GetMapping("/services/miscellaneous")
    public String gotoMiscellaneousServices(){

        return "/services/serviceCategory";
    }

    @GetMapping("/services/categories")
    public String gotoCategories(){

        return "/services/serviceCategories";
    }

    @GetMapping("/services/honeydoer/profile")
    public String gotoHoneydoerProfile(){

        return "/services/honeydoerProfile";
    }

    @GetMapping("/user/honeydoer/dashboard/{honeydoerId}")
    public String gotoHoneydoerDashboard(Model model, @PathVariable int honeydoerId){
        Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);

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
        //allTasks = tasksDao.findAllByHoneydoerService(honeydoerId);
        model.addAttribute("tasks", allTasks);


        if(honeydoersDao.findById(honeydoerId).isPresent()){
            Honeydoers honeydoerObject = honeydoer.get();
            model.addAttribute("honeydoer", honeydoerObject);
        }

        List<HoneydoerReviews> allReviews = new ArrayList<>();
        allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoerId);
        model.addAttribute("reviews", allReviews);

        return "/users/honeydoerDashboard";
    }
}
