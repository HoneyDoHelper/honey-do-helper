package com.codeup.honeydohelper.Controllers;
import com.codeup.honeydohelper.Models.Categories;
import com.codeup.honeydohelper.Models.HoneyUsers;
import com.codeup.honeydohelper.Models.TaskCosts;
import com.codeup.honeydohelper.Models.Tasks;
import com.codeup.honeydohelper.Repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class TasksController {
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
    public TasksController (
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

    @GetMapping("/tasks/{taskId}")
    public String gotoTasks(Model model, @PathVariable int taskId) {
        setCategoriesHtml(model);


        Optional<Tasks> task = tasksDao.findById(taskId);
        if (tasksDao.findById(taskId).isPresent()) {
            Tasks taskObject = task.get();
            model.addAttribute("task", taskObject);
        }

        TaskCosts taskCost = tasksCostsDao.findByTask_Id(taskId);
        model.addAttribute("task_cost", taskCost);


        return "/services/tasks";
    }


    /*================================================================================
    Controller Methods to set model Attributes
    ================================================================================*/
    private HoneyUsers findLoggedInHoneyUser(){
        return (HoneyUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void setCategoriesHtml(Model model){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);
    }

}
