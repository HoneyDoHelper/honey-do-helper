package com.codeup.honeydohelper.Controllers;
import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ServicesController {
    private final CategoriesRepository categoriesDao;
    private final HoneydoerReviewsRepository honeydoerReviewsDao;
    private final HoneydoerServicesRepository honeydoerServicesDao;
    private final HoneydoersRepository honeydoersDao;
    private final ServicesRepository servicesDao;
    private final TaskCostsRepository tasksCostsDao;
    private final TasksRepository tasksDao;
    private final UserProfilesRepository userProfileDao;
    private final HoneyUsersRepository usersDao;

    public ServicesController(
            CategoriesRepository categoriesDao,
            HoneydoerReviewsRepository honeydoerReviewsDao,
            HoneydoerServicesRepository honeydoerServicesDao,
            HoneydoersRepository honeydoersDao,
            ServicesRepository servicesDao,
            TaskCostsRepository tasksCostsDao,
            TasksRepository tasksDao,
            UserProfilesRepository userProfileDao,
            HoneyUsersRepository usersDao
    ) {
        this.categoriesDao = categoriesDao;
        this.honeydoerReviewsDao = honeydoerReviewsDao;
        this.honeydoerServicesDao = honeydoerServicesDao;
        this.honeydoersDao = honeydoersDao;
        this.servicesDao = servicesDao;
        this.tasksCostsDao = tasksCostsDao;
        this.tasksDao = tasksDao;
        this.userProfileDao = userProfileDao;
        this.usersDao = usersDao;
    }

    @Value("${filestack.key}")
    private String filestackKey;

    @GetMapping("/categories/{categoryId}")
    public String gotoCategory(Model model, @PathVariable int categoryId) {
        setCategoriesHtml(model);

        setCategoryHtml(model, categoryId);
        setServiceCategoryHtml(model, categoryId);

        return "services/serviceCategory";
    }


    @GetMapping("/services/{serviceId}")
    public String gotoServices(Model model, @PathVariable int serviceId) {
        setCategoriesHtml(model);

        setAllUserProfilesHtml(model);
        setServiceHtml(model, serviceId);
        setAllHoneydoerServicesHtml(model, serviceId);

        return "services/services";
    }


    @GetMapping("/categories")
    public String gotoCategories(Model model) {
        setCategoriesHtml(model);

        return "services/serviceCategories";
    }


    @GetMapping("/services/honeydoer/{honeydoerId}/{serviceId}")
    public String gotoHoneydoerProfile(Model model, @PathVariable int honeydoerId, @PathVariable int serviceId) {
        setCategoriesHtml(model);

        setAllUserProfilesHtml(model);
        setHoneydoerHtml(model, honeydoerId);
        setHoneydoerServiceHtml(model, serviceId, honeydoerId);
        setReviewsHtml(model, honeydoerId);

        return "services/honeydoerProfile";
    }


    @GetMapping("/services/bookService/{honeydoerId}/{serviceId}")
    public String gotoBookService(Model model, @PathVariable int honeydoerId, @PathVariable int serviceId) {
        setCategoriesHtml(model);

        HoneyUsers currentLoggedInUser = findLoggedInHoneyUser();
        setUserHtml(model, currentLoggedInUser);

        setAllUserProfilesHtml(model);
        setHoneydoerHtml(model, honeydoerId);
        setHoneydoerServiceHtml(model, serviceId, honeydoerId);
        setReviewsHtml(model, honeydoerId);

        model.addAttribute("newTask", new Tasks());
        model.addAttribute("filestackKey", filestackKey);

        return "services/bookService";
    }


    @PostMapping("/services/bookService")
    public String submitProposal(@ModelAttribute Tasks task,
                                 @RequestParam("honeydoerServiceId") String honeydoerServiceId,
                                 @RequestParam("honeyUserId") String honeyUserId,
                                 @RequestParam("taxes") float taxes,
                                 @RequestParam("totalUserCost") float totalUserCost,
                                 @RequestParam("sitePay") float sitePay,
                                 @RequestParam("honeydoerPay") float honeydoerPay) {

        createTask(task, honeydoerServiceId, honeyUserId);
//        createHoneydoerImage(imageUrl, honeydoerId);

        TaskCosts taskCostToSave = new TaskCosts();

        taskCostToSave.setTaxes(taxes);
        taskCostToSave.setSitePay(sitePay);
        taskCostToSave.setTotalUserCost(totalUserCost);
        taskCostToSave.setHoneydoerPay(honeydoerPay);

        taskCostToSave.setTask(task);
        tasksCostsDao.save(taskCostToSave);
        return "redirect:/dashboard";
    }


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

    private void setCategoryHtml(Model model, int categoryId) {
        Optional<Categories> category = categoriesDao.findById(categoryId);

        if (categoriesDao.findById(categoryId).isPresent()) {
            Categories categoryObject = category.get();
            model.addAttribute("category", categoryObject);
        }
    }

    private void setServiceCategoryHtml(Model model, int categoryId) {
        List<Services> allServices = servicesDao.findAllByCategory_Id(categoryId);
        model.addAttribute("services", allServices);
    }

    private void setServiceHtml(Model model, int serviceId) {
        Optional<Services> service = servicesDao.findById(serviceId);
        if (servicesDao.findById(serviceId).isPresent()) {
            Services serviceObject = service.get();
            model.addAttribute("service", serviceObject);
        }
    }

    private void setAllHoneydoerServicesHtml(Model model, int serviceId) {
        List<HoneydoerServices> allHoneydoerServices = honeydoerServicesDao.findAllByServices_Id(serviceId);
        model.addAttribute("honeydoerServices", allHoneydoerServices);
    }

    private void setHoneydoerServiceHtml(Model model, int serviceId, int honeydoerId) {
        HoneydoerServices honeydoerService = honeydoerServicesDao.findByServices_IdAndHoneydoers_Id(serviceId, honeydoerId);
        model.addAttribute("service", honeydoerService);
    }

    private void setHoneydoerHtml(Model model, int honeydoerId) {
        Optional<Honeydoers> honeydoer = honeydoersDao.findById(honeydoerId);
        if (honeydoersDao.findById(honeydoerId).isPresent()) {
            Honeydoers honeydoerObject = honeydoer.get();
            model.addAttribute("honeydoer", honeydoerObject);
        }
    }

    private void setReviewsHtml(Model model, int honeydoerId) {
        List<HoneydoerReviews> allReviews = honeydoerReviewsDao.findAllByHoneydoer_Id(honeydoerId);
        model.addAttribute("reviews", allReviews);
    }

    private void setAllUserProfilesHtml(Model model) {
        List<UserProfiles> allUserProfiles = userProfileDao.findAll();
        model.addAttribute("userProfiles", allUserProfiles);
    }


    /*================================================================================
    Controller Methods to Create Tasks
    ================================================================================*/
    private void createTask(Tasks task, String honeydoerServiceId, String honeyUserId) {
        Optional<HoneydoerServices> honeydoerService = honeydoerServicesDao.findById(Integer.parseInt(honeydoerServiceId));
        if (honeydoerService.isPresent()) {
            HoneydoerServices honeydoerObject = honeydoerService.get();
            task.setHoneydoerService(honeydoerObject);
        }

        Optional<HoneyUsers> user = usersDao.findById(Integer.parseInt(honeyUserId));
        if (user.isPresent()) {
            HoneyUsers userObject = user.get();
            task.setUser(userObject);
        }

//        task.setIsAccepted(null);
//        task.setIsCompleted(null);
        tasksDao.save(task);
    }
}