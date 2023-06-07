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
@RequestMapping("/honeydoerServices")
public class HoneydoerServicesController {

    private final HoneydoerServicesRepository servicesRepository;

    @Autowired
    public HoneydoerServicesController(HoneydoerServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    // details of honeydoer services
    @GetMapping("/{id}")
    public String getHoneydoerService(@PathVariable("id") int id, Model model) {
        HoneydoerServices service = servicesRepository.findById(id).orElse(null);
        if (service != null) {
            model.addAttribute("service", service);
            return "honeydoerServiceDetails"; //  add correct re direct
        }
        return "redirect:/honeydoerServices"; //  add correct re direct
    }

    // show form to create honeydoer services
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("service", new HoneydoerServices());
        return "createService"; //  add correct re direct
    }

    // create new honeydoer service
    @PostMapping("/create")
    public String createHoneydoerService(@ModelAttribute("service") HoneydoerServices service) {
        servicesRepository.save(service);

        return "redirect:/honeydoerServices"; //  add correct re direct
    }


    // form to edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        HoneydoerServices service = servicesRepository.findById(id).orElse(null);
        if (service != null) {
            model.addAttribute("service", service);
            return "editService"; //  add correct re direct
        }
        return "redirect:/honeydoerServices"; //  add correct re direct
    }

    // update serivces
    @PostMapping("/edit/{id}")
    public String updateHoneydoerService(@PathVariable("id") int id, @ModelAttribute("service") HoneydoerServices updatedService) {
        HoneydoerServices service = servicesRepository.findById(id).orElse(null);
        if (service != null) {
            service.setRate(updatedService.getRate());
            service.setAboutService(updatedService.getAboutService());
            servicesRepository.save(service);
        }
        return "redirect:/honeydoerServices"; //  add correct re direct
    }

    // delete services
    @PostMapping("/delete/{id}")
    public String deleteHoneydoerService(@PathVariable("id") int id) {
        servicesRepository.deleteById(id);
        return "redirect:/honeydoerServices"; //  add correct re direct
    }
}
