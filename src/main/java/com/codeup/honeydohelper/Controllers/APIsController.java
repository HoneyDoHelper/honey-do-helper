package com.codeup.honeydohelper.Controllers;

import com.codeup.honeydohelper.Repositories.ChatsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class APIsController {
    private final ChatsRepository chatsDao;

    public APIsController (
            ChatsRepository chatsDao
    ){
        this.chatsDao = chatsDao;
    }

    @Value("${talkjs.api.key}")
    private String apiKey;

    @GetMapping("/chat")
    public String chatPage(Model model) {
        model.addAttribute("apiKey", apiKey);
        return "/apis/chat";
    }

    @GetMapping("/calendar")
    public String showCalendar(Model model) {
        // You can add any necessary model attributes if needed
        return "/apis/calendar";
    }
}