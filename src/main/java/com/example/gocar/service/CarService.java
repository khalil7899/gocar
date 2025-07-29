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

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car update(Long id, Car updatedCar) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setModel(updatedCar.getModel());
        car.setAvailable(updatedCar.isAvailable());
        car.setParking(updatedCar.getParking());
        return carRepository.save(car);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> getAvailableCars() {
        return carRepository.findByAvailableTrue();
    }
}
