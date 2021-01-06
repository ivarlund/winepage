package com.project.uniqo.controllers;

import com.project.uniqo.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("/wines")
    public String winePage(Model model) {
        model.addAttribute("Wines", wineService.fetchWineDbData());

        return "wines";
    }

    @PostMapping("/wines")
    public String winePageSort(@RequestParam String sort, Model model) {
        model.addAttribute("Wines", wineService.sortWineDbData(sort));
        return "wines";
    }

    // Basic select *
    @GetMapping("/winess")
    public String winePage2(Model model) {
        model.addAttribute("Wines", wineService.fetchWineDbData2());
        return "winess";
    }
}
