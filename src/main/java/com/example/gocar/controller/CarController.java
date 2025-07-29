package com.example.gocar.controller;

import com.example.gocar.model.Car;
import com.example.gocar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin/cars")
public class CarController {
    @Autowired
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @GetMapping("/")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return ResponseEntity.ok(carService.update(id, updatedCar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }
}
