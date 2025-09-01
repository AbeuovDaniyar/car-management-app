package com.carmanagment.config;

import com.carmanagment.entity.Car;
import com.carmanagment.entity.User;
import com.carmanagment.repository.CarRepository;
import com.carmanagment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
            logger.info("Admin user created");
        }
        
        // Create regular user if not exists
        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("USER");
            userRepository.save(user);
            logger.info("Regular user created");
        }
        
        // Add sample cars if none exist
        if (carRepository.count() == 0) {
            Car car1 = new Car("Toyota", "Camry", 2023, new BigDecimal("28999.99"), "Reliable sedan with excellent fuel economy");
            Car car2 = new Car("Honda", "Civic", 2022, new BigDecimal("24999.99"), "Compact car perfect for city driving");
            Car car3 = new Car("Ford", "Mustang", 2023, new BigDecimal("35999.99"), "Classic American muscle car");
            Car car4 = new Car("BMW", "3 Series", 2023, new BigDecimal("42999.99"), "Luxury sedan with premium features");
            
            carRepository.save(car1);
            carRepository.save(car2);
            carRepository.save(car3);
            carRepository.save(car4);
            logger.info("Sample cars added");
        }
    }
}
