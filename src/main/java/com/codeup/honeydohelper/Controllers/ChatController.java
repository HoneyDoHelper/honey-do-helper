package com.codeup.honeydohelper.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ChatController {


    @Value("${talkjs.api.key}")
    private String apiKey;

    @GetMapping("/chat")
    public String chatPage(Model model) {
        model.addAttribute("apiKey", apiKey);
        return "chat";
    }
}
