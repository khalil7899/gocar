package com.example.gocar.service;

import com.example.gocar.model.Car;
import com.example.gocar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAvailableCars() {
        return carRepository.findByAvailableTrue();
    }
}
