package com.example.spring1.api.controller;
import com.example.spring1.api.dto.CarDTO;
import com.example.spring1.api.entity.Car;
import com.example.spring1.api.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public String getAllCars(Model model) {
        List<CarDTO> cars = carService.getAllCars();

        if (!cars.isEmpty()) {
            model.addAttribute("cars", cars);
            return "all-cars";
        } else {
            return "error/404";
        }
    }

    @PostMapping("/create-car")
    public String createCar(@ModelAttribute Car car,
                            @RequestParam(required = false) Long dealerId,
                            Model model) {

        CarDTO newCarDto = carService.createCar(car, dealerId);

        if (newCarDto != null) {
            if (dealerId != null) {
                return "redirect:/api/dealerships/" + dealerId;
            }

            model.addAttribute("newCar", newCarDto);
            return "created-car";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/{vinid}")
    public String getCarByVinid(@PathVariable Long vinid, Model model) {
        Optional<CarDTO> carDTO = carService.getCarByVinid(vinid);

        if (carDTO.isPresent()) {
            model.addAttribute("car", carDTO.get());
            return "car-details";
        } else {
            return "error/404";
        }
    }

    @PostMapping("/{vinid}/update")
    public String updateCar(@PathVariable Long vinid, String make, String model, String color) {
        boolean updated = carService.updateCar(vinid, make, model, color);

        if (updated) {
            return "redirect:/";
        } else {
            return "error/404";
        }
    }

    @PostMapping("/{vinid}/delete")
    public String deleteCar(@PathVariable Long vinid, Model model) {
        Optional<CarDTO> deletedCar = carService.deleteCar(vinid);

        if (deletedCar.isPresent()) {
            model.addAttribute("deletedCar", deletedCar.get());
            return "deleted-confirmation";
        } else {
            return "error/404";
        }
    }
}