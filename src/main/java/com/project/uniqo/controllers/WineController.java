package com.project.uniqo.controllers;

import com.project.uniqo.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("/wines")
    public String winePage(Model model) {
        model.addAttribute("Wines", wineService.fetchWineDbData());
        return "wines";
    }

    // NEW WINEPAGE
    @GetMapping("/winess")
    public String winePage2(Model model) {
        model.addAttribute("Wines", wineService.fetchWineDbData2());
        return "winess";
    }
}
