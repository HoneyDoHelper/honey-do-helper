package com.codeup.honeydohelper.Controllers;
import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;
@Controller
@RequestMapping("/honeydoers")
public class HoneydoersController {

    private final HoneydoersRepository honeydoersDao;

    @Autowired
    public HoneydoersController(HoneydoersRepository honeydoersDao) {
        this.honeydoersDao = honeydoersDao;
    }

    @GetMapping("/{id}")
    public String getHoneydoer(@PathVariable int id, Model model) {
        Honeydoers honeydoer = honeydoersDao.findById(id).orElse(null); // will find honeydoer by id
        if (honeydoer == null) {
            return "redirect:/error"; // if no honeydoer found it will give an error
        }
        model.addAttribute("honeydoer", honeydoer);
        return "honeydoerProfile"; // add proper redirect
    }

    @PostMapping("/{id}/update")
    public String updateHoneydoer(@PathVariable int id, @RequestParam float rating, @RequestParam String aboutSelf) {
        Honeydoers honeydoer = honeydoersDao.findById(id).orElse(null); // will find honeydoer by id
        if (honeydoer == null) {
            return "redirect:/error"; // if no honeydoer found it will give an error
        }
        honeydoer.setRating(rating); // updates the Honeydoer rating
        honeydoer.setAboutSelf(aboutSelf);
        honeydoersDao.save(honeydoer); // saves the updated info
        return "redirect:/honeydoers/" + id; // add proper redirect
    }
}
