package com.project.uniqo.controllers;

import com.project.uniqo.services.ProducerService;
import com.project.uniqo.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    WineService wineService;

    @Autowired
    ProducerService producerService;

    @PostMapping("/searchResult")
    public String searchResult(@RequestParam String searchTerm, Model wineModel, Model producerModel) {
        wineModel.addAttribute("Wines", wineService.fetchWineBySearch(searchTerm));
        producerModel.addAttribute("Producers", producerService.fetchProducerBySearch(searchTerm));

        return "searchResult";
    }

}