package com.project.uniqo.controllers;

import com.project.uniqo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @Autowired
    EmailService emailService;

    @GetMapping("/contact")
    public String contact() {

        emailService.sendSimpleMessage("ivarnilslund@gmail.com","springmvc", "this is a test email!");

        return "contact";
    }

}
