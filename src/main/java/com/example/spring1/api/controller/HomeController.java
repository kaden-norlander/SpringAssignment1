package com.example.spring1.api.controller;

import com.example.spring1.api.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ManufacturerService manufacturerService;

    public HomeController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        return "index";
    }
}
