package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Models.*;
import com.codeup.honeydohelper.Repositories.*;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Optional;
import com.codeup.honeydohelper.Models.Honeydoers;
import java.util.List;
import java.util.ArrayList;
import com.codeup.honeydohelper.Models.HoneydoerServices;
import com.codeup.honeydohelper.Models.Tasks;
import com.codeup.honeydohelper.Models.HoneydoerReviews;




import java.util.List;

@Controller
public class IndexController {

    private final CategoriesRepository categoriesDao;


    public IndexController (
            CategoriesRepository categoriesDao
    ){
        this.categoriesDao = categoriesDao;
    }


    /*/////////////////////////////////////////////////////////
    /Index  |  /Contact  |  /About  |  /Support
    /////////////////////////////////////////////////////////*/

    @GetMapping("/")
    public String goHome(Model model) {
        displayServiceCategoriesForNav(model);

        return "index";
    }
    @GetMapping("/index")
    public String gotoIndex(Model model) {
        displayServiceCategoriesForNav(model);

        return "index";
    }
    @GetMapping("/contact")
    public String gotoContact(Model model){
        displayServiceCategoriesForNav(model);

        return "contact";
    }

    @GetMapping("/about")
    public String gotoAbout(Model model) {
        displayServiceCategoriesForNav(model);

        return "about";
    }

    @GetMapping("/support")
    public String gotoSupport(Model model){
        displayServiceCategoriesForNav(model);

        return "support";
    }


    private void displayServiceCategoriesForNav(Model model) {
        List<Categories> allCategories = categoriesDao.findAll();
        model.addAttribute("categories", allCategories);
    }
}
