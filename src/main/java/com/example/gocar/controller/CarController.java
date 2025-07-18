package com.example.gocar.controller;

import com.example.gocar.model.Car;
import com.example.gocar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }
}
