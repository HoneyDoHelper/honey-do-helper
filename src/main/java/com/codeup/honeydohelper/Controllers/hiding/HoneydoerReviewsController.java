package com.codeup.honeydohelper.Controllers.hiding;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;
@Controller
@RequestMapping("/honeydoerReviews")
public class HoneydoerReviewsController {

    private final HoneydoerReviewsRepository reviewsRepository;
    private final TasksRepository tasksRepository;

    @Autowired
    public HoneydoerReviewsController(HoneydoerReviewsRepository reviewsRepository, TasksRepository tasksRepository) {
        this.reviewsRepository = reviewsRepository;
        this.tasksRepository = tasksRepository;
    }

    @PostMapping
    // adds Honeydoer review
    public String addHoneydoerReview(@ModelAttribute("review") HoneydoerReviews review, @RequestParam("taskId") Long taskId)  {
        return "redirect:/tasks/" + taskId; //  add correct re direct
    }

    //form to edit
    @GetMapping("/edit/{id}")
    public String showEditReviewForm(@PathVariable("id") Long id, Model model) {
        return "editReview"; //  add correct re direct
    }

    // update
    @PostMapping("/edit/{id}")
    public String updateHoneydoerReview(@PathVariable("id") Long id, @ModelAttribute("review") HoneydoerReviews updatedReview) {
        return "redirect:/honeydoerReviews"; //  add correct re direct
    }

    // delete a review
    @PostMapping("/delete/{id}")
    public String deleteHoneydoerReview(@PathVariable("id") Long id) {
        return "redirect:/honeydoerReviews"; //  add correct re direct
    }
}
