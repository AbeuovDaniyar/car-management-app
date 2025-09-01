package com.carmanagment.controller;

import com.carmanagment.entity.Car;
import com.carmanagment.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    @Autowired
    private CarService carService;
    
    @GetMapping
    public String adminPage(Model model) {
        logger.info("Accessing admin page");
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("newCar", new Car());
        return "admin";
    }
    
    @PostMapping("/cars")
    public String addCar(@ModelAttribute Car car) {
        logger.info("Adding new car: {} {}", car.getMake(), car.getModel());
        carService.saveCar(car);
        return "redirect:/admin";
    }
    
    @PostMapping("/cars/{id}/delete")
    public String deleteCar(@PathVariable Long id) {
        logger.info("Deleting car with ID: {}", id);
        carService.deleteCar(id);
        return "redirect:/admin";
    }
    
    @GetMapping("/cars/{id}/edit")
    public String editCarForm(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id).orElse(null);
        if (car != null) {
            model.addAttribute("car", car);
            return "edit-car";
        }
        return "redirect:/admin";
    }
    
    @PostMapping("/cars/{id}/edit")
    public String updateCar(@PathVariable Long id, @ModelAttribute Car car) {
        logger.info("Updating car with ID: {}", id);
        car.setId(id);
        carService.updateCar(car);
        return "redirect:/admin";
    }
}
