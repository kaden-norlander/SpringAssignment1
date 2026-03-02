package com.example.spring1.api.controller;

import com.example.spring1.api.dto.DealershipDTO;
import com.example.spring1.api.entity.Dealership;
import com.example.spring1.api.service.DealershipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/dealerships")
public class DealershipController {

    private final DealershipService dealershipService;

    public DealershipController(DealershipService dealershipService) {
        this.dealershipService = dealershipService;
    }

    @GetMapping("/all")
    public String getAllDealerships(Model model) {
        List<DealershipDTO> dealerships = dealershipService.getAllDealerships();

        model.addAttribute("dealerships", dealerships);
        return "all-dealerships";
    }

    @PostMapping("/create")
    public String createDealership(@ModelAttribute Dealership dealership,
                                   @RequestParam(required = false) Long manufacturerId,
                                   Model model) {

        DealershipDTO newDealershipDto = dealershipService.createDealership(dealership, manufacturerId);

        if (newDealershipDto != null) {
            model.addAttribute("newDealership", newDealershipDto);
            return "created-dealership";
        } else {
            return "error/404";
        }
    }

    @PostMapping("/{dealershipId}/delete")
    public String deleteDealership(@PathVariable Long dealershipId, Model model) {
        Optional<DealershipDTO> deletedDealership = dealershipService.deleteDealership(dealershipId);

        if (deletedDealership.isPresent()) {
            model.addAttribute("deletedDealership", deletedDealership.get());
            return "deleted-dealership-confirmation";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/{dealershipId}")
    public String getDealershipDetails(@PathVariable Long dealershipId, Model model) {
        Optional<DealershipDTO> dealershipOpt = dealershipService.getDealershipById(dealershipId);

        if (dealershipOpt.isPresent()) {
            model.addAttribute("dealership", dealershipOpt.get());
            return "dealership-details";
        } else {
            return "error/404";
        }
    }
}
