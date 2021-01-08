package com.project.uniqo.controllers;

import com.project.uniqo.services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @GetMapping("/producers")
    public String producerPage(Model model) {
        model.addAttribute("Producers", producerService.getAllProducers());
        return "producers";
    }

}
