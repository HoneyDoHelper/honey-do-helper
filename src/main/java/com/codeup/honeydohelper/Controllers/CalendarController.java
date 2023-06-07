package com.codeup.honeydohelper.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String showCalendar(Model model) {
        // You can add any necessary model attributes if needed
        return "calendar";
    }
}
