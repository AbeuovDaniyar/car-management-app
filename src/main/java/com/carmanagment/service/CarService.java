package com.carmanagment.service;

import com.carmanagment.entity.Car;
import com.carmanagment.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private static final Logger logger = LoggerFactory.getLogger(CarService.class);
    
    @Autowired
    private CarRepository carRepository;
    
    public List<Car> getAllCars() {
        logger.info("Fetching all cars");
        return carRepository.findAll();
    }
    
    public Car saveCar(Car car) {
        logger.info("Saving car: {} {}", car.getMake(), car.getModel());
        return carRepository.save(car);
    }
    
    public Optional<Car> getCarById(Long id) {
        logger.info("Fetching car with ID: {}", id);
        return carRepository.findById(id);
    }
    
    public void deleteCar(Long id) {
        logger.info("Deleting car with ID: {}", id);
        carRepository.deleteById(id);
    }
    
    public Car updateCar(Car car) {
        logger.info("Updating car with ID: {}", car.getId());
        return carRepository.save(car);
    }
}
