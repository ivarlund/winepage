package com.project.uniqo.controllers;

import com.project.uniqo.models.Wine;
import com.project.uniqo.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("/wines")
    public String winePage(Model model) {
        model.addAttribute("Wines", wineService.getAllWines());

        return "wines";
    }

    //    @PostMapping("/wines")
//    public String getWinesSorted(@RequestParam(name = "filter", required = false) String filter, @RequestParam(name = "sort", required = false) String sort,  Model model) {
//        if (sort != null) {
//            model.addAttribute("Wines", wineService.getAllWinesSorted(sort));
//            System.out.println(sort);
//        } else if (sort != null) {
//            model.addAttribute("Wines", wineService.getWinesFiltered(filter));
//            System.out.println("FILTER:  " + filter);
//        }
//        return "wines";
//    }
    @GetMapping("/wines/sort/{sort}")
    public String getWinesSorted(@PathVariable String sort, Model model) {
        model.addAttribute("Wines", wineService.getAllWinesSorted(sort));
        System.out.println("SORT:" +sort);
        return "wines";
    }

    @PostMapping("/wines")
    public String getWinesFiltered(@RequestParam(name = "filter", required = false) String filter, Model model) {
        model.addAttribute("Wines", wineService.getWinesFiltered(filter));
        System.out.println("FILTER:  " + filter);
        return "wines";
    }

    // Basic select *
    @GetMapping("/winess")
    public String winePage2(Model model) {
        model.addAttribute("Wines", wineService.getAllWines2());
        return "winess";
    }
}
