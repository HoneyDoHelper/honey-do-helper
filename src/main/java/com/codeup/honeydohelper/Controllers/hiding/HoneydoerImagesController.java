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
@RequestMapping("/honeydoerImages")
public class HoneydoerImagesController {

    private final HoneydoerImagesRepository imagesRepository;

    @Autowired
    public HoneydoerImagesController(HoneydoerImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @GetMapping("/{id}")
    public String getHoneydoerImage(@PathVariable int id, Model model) {
        HoneydoerImages image = imagesRepository.findById(id).orElse(null); // will find honeydoer by id
        if (image == null) {
            return "redirect:/error"; // if no honeydoer found it will give an error
        }
        model.addAttribute("image", image); // adds the image to view
        return "honeydoerImage"; // add proper redirect
    }

    @PostMapping("/add")
    public String addHoneydoerImage(@ModelAttribute HoneydoerImages image) {
        imagesRepository.save(image); // saves the honeydoer image
        return "redirect:/honeydoerImages/" + image.getId(); // add proper redirect
    }

    @PostMapping("/{id}/delete")
    public String deleteHoneydoerImage(@PathVariable int id) {
        imagesRepository.deleteById(id);  // deletes the honeydoer image
        return "redirect:/honeydoerImages"; // add proper redirect
    }
}