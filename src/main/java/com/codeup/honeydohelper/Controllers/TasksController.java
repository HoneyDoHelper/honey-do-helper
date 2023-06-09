package com.codeup.honeydohelper.Controllers;
import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class TasksController {
    private final CategoriesRepository categoriesDao;
    private final ClientReviewsRepository clientReviewsDao;
    private final HoneydoerReviewsRepository honeydoerReviewsDao;
    private final HoneydoersRepository honeydoersDao;
    private final TaskCostsRepository tasksCostsDao;
    private final TasksRepository tasksDao;
    private final UserProfilesRepository userProfileDao;
    private final HoneyUsersRepository usersDao;

    public TasksController (
            CategoriesRepository categoriesDao,
            ClientReviewsRepository clientReviewsDao,
            HoneydoerReviewsRepository honeydoerReviewsDao,
            HoneydoersRepository honeydoersDao,
            TaskCostsRepository tasksCostsDao,
            TasksRepository tasksDao,
            UserProfilesRepository userProfileDao,
            HoneyUsersRepository usersDao
    ){
        this.categoriesDao = categoriesDao;
        this.clientReviewsDao = clientReviewsDao;
        this.honeydoerReviewsDao = honeydoerReviewsDao;
        this.honeydoersDao = honeydoersDao;
        this.tasksCostsDao = tasksCostsDao;
        this.tasksDao = tasksDao;
        this.userProfileDao = userProfileDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/tasks/{taskId}")
    public String gotoTasks(Model model, @PathVariable int taskId) {
        setCategoriesHtml(model);

        //Finds current logged in user and sets it in the html doc
        HoneyUsers currentLoggedInUser = findLoggedInHoneyUser();
        setUserHtml(model, currentLoggedInUser);

        setTaskHtml(model, taskId);
        setTaskCostHtml(model, taskId);
        setAllUserProfilesHtml(model);

        Tasks task = findTask(taskId);
        Honeydoers honeydoer = new Honeydoers();

        if (clientReviewsDao.findByTask_Id(taskId) != null) {
            model.addAttribute("clientReviewed", true);
        } else {
            model.addAttribute("clientReviewed", false);
        }

        if (honeydoerReviewsDao.findByTask_Id(taskId) != null) {
            model.addAttribute("honeydoerReviewed", true);
        } else {
            model.addAttribute("honeydoerReviewed", false);
        }

        if(currentLoggedInUser.getIsHoneydoer()){
            honeydoer = findHoneydoer(currentLoggedInUser.getId());
        }

        //Puts user in appropriate dashboard based on isAdmin or isHoneydoer
        if (currentLoggedInUser.getIsAdmin()) {

            return "services/tasks";

        } else if (currentLoggedInUser.getIsHoneydoer()
                && currentLoggedInUser.getId() == honeydoer.getUser().getId()
                && task.getHoneydoerService().getHoneydoers().getId() == honeydoer.getId()) {

            model.addAttribute("isHoneydoer", true);

            return "services/tasks";

        } else if (currentLoggedInUser.getId() == task.getUser().getId()){

            model.addAttribute("isHoneydoer", false);

            return "services/tasks";

        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/tasks/update")
    public String updateTasks(Model model,
                              @RequestParam("task-id") int taskId,
                              @RequestParam("todo-status") String status){

        Optional<Tasks> task = tasksDao.findById(taskId);
        Tasks taskObject = task.get();

        if (status.equals("Accepted")){

            System.out.println("Accepted task = " + taskId);
            taskObject.setIsAccepted(true);

        } else if (status.equals("Completed")) {

            System.out.println("Completed task = " + taskId);
            taskObject.setIsCompleted(true);

        } else {
            System.out.println("Declined task = " + taskId);
            taskObject.setIsDeclined(true);
        }

        tasksDao.save(taskObject);


        return "redirect:/tasks/" + taskId;
    }

    @PostMapping("/tasks/review")
    public String reviewTasks(Model model,
                              @RequestParam("task-id") int taskId,
                              @RequestParam("isHoneydoer") boolean isHoneydoer,
                              @RequestParam("review-id") int reviewId,
                              @RequestParam("stars") String starInput,
                              @RequestParam("comment") String comment){

        Optional<Tasks> task = tasksDao.findById(taskId);
        Tasks taskObject = task.get();

        Stars stars = setStars(starInput);

        if (isHoneydoer){

            Optional<HoneyUsers> honeyUser = usersDao.findById(reviewId);
            HoneyUsers honeyUserObject = honeyUser.get();

            ClientReviews review = new ClientReviews();
            review.setTask(taskObject);
            review.setUser(honeyUserObject);
            review.setStars(stars);
            review.setComment(comment);

            clientReviewsDao.save(review);

        } else {

            Optional<Honeydoers> honeydoer = honeydoersDao.findById(reviewId);
            Honeydoers honeydoerObject = honeydoer.get();

            float currentRating = honeydoerObject.getRating();
            float newReviewRating = getRatingValue(starInput);

            if(currentRating <=0){
                honeydoerObject.setRating(newReviewRating);
            } else {
                honeydoerObject.setRating((currentRating + newReviewRating) / 2);
            }

            HoneydoerReviews review = new HoneydoerReviews();
            review.setTask(taskObject);
            review.setHoneydoer(honeydoerObject);
            review.setStars(stars);
            review.setComment(comment);

            honeydoerReviewsDao.save(review);
        }

        return "redirect:/tasks/" + taskId;
    }

    /*================================================================================
    Controller Methods to set model Attributes
    ================================================================================*/
    private HoneyUsers findLoggedInHoneyUser(){
        return (HoneyUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void setUserHtml(Model model, HoneyUsers honeyUser){

        model.addAttribute("user", honeyUser);
    }

    private void setCategoriesHtml(Model model){
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);
    }

    private void  setTaskHtml(Model model, int taskId){
        Optional<Tasks> task = tasksDao.findById(taskId);
        if (tasksDao.findById(taskId).isPresent()) {
            Tasks taskObject = task.get();
            model.addAttribute("task", taskObject);
        }
    }

    private void setTaskCostHtml(Model model, int taskId){
        TaskCosts taskCost = tasksCostsDao.findByTask_Id(taskId);
        model.addAttribute("task_cost", taskCost);
    }

    private Tasks findTask(int taskId){
        Optional<Tasks> task = tasksDao.findById(taskId);
        if (tasksDao.findById(taskId).isPresent()) {
            Tasks taskObject = task.get();
            return taskObject;
        } else {
            return null;
        }
    }

    private Honeydoers findHoneydoer(int userId){
        return honeydoersDao.findByUser_Id(userId);
    }

    private void setAllUserProfilesHtml(Model model) {
        List<UserProfiles> allUserProfiles = userProfileDao.findAll();
        model.addAttribute("userProfiles", allUserProfiles);
    }

    private Stars setStars (String starInput){
        return switch (starInput) {
            case "ONE" -> Stars.ONE;
            case "TWO" -> Stars.TWO;
            case "THREE" -> Stars.THREE;
            case "FOUR" -> Stars.FOUR;
            default -> Stars.FIVE;
        };
    }

    private float getRatingValue (String starInput){
        return switch (starInput) {
            case "ONE" -> 1.0f;
            case "TWO" -> 2.0f;
            case "THREE" -> 3.0f;
            case "FOUR" -> 4.0f;
            default -> 5.0f;
        };
    }


}
