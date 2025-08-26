package com.johnray100.io.rabbitmq_message_status.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/messages")
    public String messagesPage() {
        return "views/messages"; // This loads messages.html
    }

}
