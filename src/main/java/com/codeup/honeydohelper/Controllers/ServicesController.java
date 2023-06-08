package com.codeup.honeydohelper.Controllers;
import com.codeup.honeydohelper.Repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    private final UserProfileRepository userProfileDao;
    private final UsersRepository usersDao;
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
            UserProfileRepository userProfileDao,
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
        this.userProfileDao = userProfileDao;
        this.usersDao = usersDao;
    }
    @GetMapping("/services")
    public String listServices(Model model) {
        model.addAttribute("posts", servicesDao.findAll());
        return "services";
    }
    @GetMapping("/service/Categories")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoriesDao.findAll());
        return "serviceCategories";
    }
    @GetMapping("/services/Categories/{id}")
    public String showExteriorServices(Model model){
        model.addAttribute("exterior", categoriesDao.findById(1));
        return "/services/serviceCategory";
    }
    @GetMapping("/services/Categories/{id}")
    public String showInteriorServices(Model model){
        model.addAttribute("interior", categoriesDao.findById(2));
        return "/services/serviceCategory";
    }
    @GetMapping("/services/Categories/{id}")
    public String showMiscellaneousServices(Model model){
        model.addAttribute("miscellaneous", categoriesDao.findById(3));
        return "/services/serviceCategory";
    }
    @GetMapping("/services/honeydoer/profile")
    public String showHoneydoerProfile(Model model){
        model.addAttribute("honeydoer", honeydoerServicesDao.findAll());
        return "/services/honeydoerProfile";
    }
}
