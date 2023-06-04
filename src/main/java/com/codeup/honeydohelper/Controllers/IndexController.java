package com.codeup.honeydohelper.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Controller
public class IndexController {


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

    @GetMapping("/services/Categories")
    public String gotoCategories(){

        return "/services/serviceCategories";
    }
}
