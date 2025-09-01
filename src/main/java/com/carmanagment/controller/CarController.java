package com.carmanagment.controller;

import com.carmanagment.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    
    @Autowired
    private CarService carService;
    
    @GetMapping("/cars")
    public String listCars(Model model) {
        logger.info("Displaying cars page");
        model.addAttribute("cars", carService.getAllCars());
        return "cars";
    }
}
