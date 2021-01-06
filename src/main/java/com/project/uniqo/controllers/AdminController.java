package com.project.uniqo.controllers;

import com.project.uniqo.models.*;
import com.project.uniqo.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    WineService wineService;

    @GetMapping("/admin")
    public String adminPage(Model wineModel, Model producerModel, Model grapeModel, Model wineGrapeModel) {
        wineModel.addAttribute("Wine", new Wine());
        producerModel.addAttribute("Producer", new Producer());
        grapeModel.addAttribute("Grape", new Grape());
        wineGrapeModel.addAttribute("WineGrape", new WineGrape());
        return "admin";
    }

    @PostMapping("/admin")
    public String adminInsert(@ModelAttribute Wine wine,
                              @ModelAttribute Producer producer,
                              @ModelAttribute Grape grape,
                              @ModelAttribute WineGrape wineGrape,
                              Model model) {

        wineService.addWine(wine);
//        model.addAttribute("Wine", wine);
        return "admin";
    }


}

