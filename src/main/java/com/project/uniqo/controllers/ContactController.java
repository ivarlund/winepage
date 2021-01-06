package com.project.uniqo.controllers;

import com.project.uniqo.models.ContactForm;
import com.project.uniqo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    EmailService emailService;

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    @PostMapping("/contact")
    public String sendContactForm(@ModelAttribute ContactForm contactForm, Model model) {
        emailService.sendSimpleMessage(contactForm);
        model.addAttribute("contactForm", contactForm);
        return "sent";

    }
//    @PostMapping
//    public String sendContactForm(@RequestParam String subject, @RequestParam String text) {
//        emailService.sendSimpleMessage("ivarnilslund@gmail.com","springmvc", "this is a test email!");
//
//        return "contact";
//    }

}
