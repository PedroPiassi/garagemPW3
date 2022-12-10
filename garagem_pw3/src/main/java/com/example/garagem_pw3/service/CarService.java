package com.example.garagem_pw3.service;

import com.example.garagem_pw3.DAO.CarDAO;
import com.example.garagem_pw3.domain.Car;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {
    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Transactional
    public Car save(Car car) {
        return carDAO.save(car);
    }

    public List<Car> findAll() {
        return carDAO.findAll();
    }

    public Optional<Car> findByID(UUID id) {
        return carDAO.findById(id);
    }

    @Transactional
    public void delete(Car car) {
        carDAO.delete(car);
    }

    public boolean existsByPlate(String plate) {
        return carDAO.existsByPlate(plate);
    }

    public List<Car> findByOwnerID(UUID owner_id) {
        return carDAO.findByOwnerId(owner_id);
    }
}