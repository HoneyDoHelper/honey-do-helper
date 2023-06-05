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
@RequestMapping("/honeydoerSchedules")
public class HoneydoerSchedulesController {

    private final HoneydoerSchedulesRepository schedulesRepository;
    private final TimeBlocksRepository timeBlocksRepository;

    @Autowired
    public HoneydoerSchedulesController(HoneydoerSchedulesRepository schedulesRepository, TimeBlocksRepository timeBlocksRepository){
        this.schedulesRepository = schedulesRepository;
        this.timeBlocksRepository = timeBlocksRepository;
    }

    // new honeydoer schedule
    @PostMapping
    public String addHoneydoerSchedule(@ModelAttribute("schedule") HoneydoerSchedules schedule, @RequestParam("timeBlockId") Long timeBlockId) {
        return "redirect:/honeydoerSchedules"; //  add correct re direct
    }

    // form to edit schedule if that is a thing we want
    @GetMapping("/edit/{id}")
    public String showEditScheduleForm(@PathVariable("id") Long id, Model model) {
        return "editSchedule"; //  add correct re direct
    }

    // update schedule
    @PostMapping("/edit/{id}")
    public String updateHoneydoerSchedule(@PathVariable("id") Long id, @ModelAttribute("schedule") HoneydoerSchedules updatedSchedule) {
        return "redirect:/honeydoerSchedules"; //  add correct re direct
    }

    // delete a schedule
    @PostMapping("/delete/{id}")
    public String deleteHoneydoerSchedule(@PathVariable("id") Long id) {
        return "redirect:/honeydoerSchedules"; //  add correct re direct
    }
}
