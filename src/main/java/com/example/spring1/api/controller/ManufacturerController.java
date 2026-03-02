package com.example.spring1.api.controller;

import com.example.spring1.api.dto.ManufacturerDTO;
import com.example.spring1.api.entity.Manufacturer;
import com.example.spring1.api.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/all")
    public String getAllManufacturers(Model model) {
        List<ManufacturerDTO> manufacturers = manufacturerService.getAllManufacturers();

        model.addAttribute("manufacturers", manufacturers);
        return "all-manufacturers";
    }

    @PostMapping("/create")
    public String createManufacturer(@ModelAttribute Manufacturer manufacturer, Model model) {
        ManufacturerDTO newManufacturerDto = manufacturerService.createManufacturer(manufacturer);

        if (newManufacturerDto != null) {
            model.addAttribute("newManufacturer", newManufacturerDto);
            return "created-manufacturer";
        } else {
            return "error/404";
        }
    }

    @PostMapping("/{manufacturerId}/delete")
    public String deleteManufacturer(@PathVariable Long manufacturerId, Model model) {
        Optional<ManufacturerDTO> deletedManufacturer = manufacturerService.deleteManufacturer(manufacturerId);

        if (deletedManufacturer.isPresent()) {
            model.addAttribute("deletedManufacturer", deletedManufacturer.get());
            return "deleted-manufacturer-confirmation";
        } else {
            return "error/404";
        }
    }
}
