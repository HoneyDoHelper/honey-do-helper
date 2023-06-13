package com.codeup.honeydohelper.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostMapping("/chat/conversation")
    public ResponseEntity<?> createConversation(@RequestParam("userId1") int userId1,
                                                @RequestParam("userId2") int userId2) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/chat/message")
    public ResponseEntity<?> sendMessage(@RequestParam("conversationId") String conversationId,
                                         @RequestParam("senderId") int senderId,
                                         @RequestParam("message") String message) {

        return ResponseEntity.ok().build();
    }

}
