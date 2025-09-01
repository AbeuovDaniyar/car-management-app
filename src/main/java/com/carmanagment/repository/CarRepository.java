package com.carmanagment.repository;

import com.carmanagment.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMakeContainingIgnoreCase(String make);
    List<Car> findByModelContainingIgnoreCase(String model);
}
