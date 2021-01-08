package com.project.uniqo.controllers;

import com.project.uniqo.models.*;
import com.project.uniqo.services.GrapeService;
import com.project.uniqo.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class AdminController {

    @Autowired
    WineService wineService;

    @Autowired
    GrapeService grapeService;

    @GetMapping("/admin")
    public String adminPage(Model wineModel, Model grapes, Model wines) {
        wineModel.addAttribute("Wine", new Wine());
        grapes.addAttribute("Grapes", grapeService.getAllGrapes());
        wines.addAttribute("Wines", wineService.getAllWines());
        return "admin";
    }

    @PostMapping("/admin")
    public ModelAndView postAddWine(@ModelAttribute Wine wine, @RequestParam(name = "grape") ArrayList<String> grapes) {
        wineService.addWine(wine, grapes);
        return new ModelAndView("redirect:/wines");
    }

    @GetMapping("/admin/edit/{id}")
    public String getEditWine(@PathVariable String id, Model wine, Model newWine, Model grapes) {
        wine.addAttribute("Wine", wineService.getWineById(id));
        grapes.addAttribute("Grapes", grapeService.getAllGrapes());
        newWine.addAttribute("newWine", new Wine());
        return "editWine";
    }

    @PostMapping("/admin/edit/{id}")
    public ModelAndView postEditWine(@ModelAttribute Wine wine, @RequestParam(name = "grape") ArrayList<String> grapes) {
        wineService.editWine(wine);
        wineService.addWineGrapes(String.valueOf(wine.getId()), grapes);
        return new ModelAndView("redirect:/wines");
    }

    @GetMapping("/admin/delete/{id}")
    public String getDeleteWine(@PathVariable String id, Model wine) {
        wine.addAttribute("Wine", wineService.getWineById(id));
        return "deleteWine";
    }

    @PostMapping("/admin/delete/{id}")
    public ModelAndView postDeleteWine(@PathVariable String id) {
        wineService.deleteWine(id);
        return new ModelAndView("redirect:/wines");
    }

}

