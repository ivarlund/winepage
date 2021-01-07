package com.project.uniqo.controllers;

import com.project.uniqo.models.*;
import com.project.uniqo.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    WineService wineService;

    @GetMapping("/admin")
    public String adminPage(Model wineModel, Model producerModel, Model grapeModel, Model wineGrapeModel, Model wines) {
        wineModel.addAttribute("Wine", new Wine());
        producerModel.addAttribute("Producer", new Producer());
        grapeModel.addAttribute("Grape", new Grape());
        wineGrapeModel.addAttribute("WineGrape", new WineGrape());
        wines.addAttribute("Wines", wineService.fetchWineDbData());
        return "admin";
    }

    @PostMapping("/admin")
    public String adminInsert(@ModelAttribute Wine wine,
                              @ModelAttribute Producer producer,
                              @ModelAttribute Grape grape,
                              @ModelAttribute WineGrape wineGrape,
                              Model wines) {

        wineService.addWine(wine);
        wines.addAttribute("Wines", wineService.fetchWineDbData());
        return "wines";
    }

    @RequestMapping(value = "/admin/{Id}", method = RequestMethod.GET)
    public String getWineForUpdate(@PathVariable String Id, Model wine, Model newWine) {
        wine.addAttribute("Wine", wineService.fetchWineById(Id));
        newWine.addAttribute("newWine", new Wine());
        return "updateWine";
    }

    @PostMapping("/admin/{id}")
    public String updateWine(@ModelAttribute Wine wine) {

        return "wines";
    }


}

