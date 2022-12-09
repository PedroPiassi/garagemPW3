package com.example.garagem_pw3.controller;

import com.example.garagem_pw3.DTO.CarDTO;
import com.example.garagem_pw3.domain.Car;
import com.example.garagem_pw3.service.CarService;
import com.example.garagem_pw3.service.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private final ClientService clientService;

    public CarController(CarService carService, ClientService clientService) {
        this.carService = carService;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable(value = "id") UUID id) {
        if (carService.findOneByID(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        return ResponseEntity.status(HttpStatus.OK).body(carService.findOneByID(id).get());
    }

    @GetMapping("/{owner_id}")
    public ResponseEntity<Object> getOneByOwnerID(@PathVariable(value = "owner_id") UUID owner_id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findByOwnerID(owner_id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid CarDTO carDTO) {
        if (carService.existsByPlate(carDTO.getPlate()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Plate already exists");
        if (clientService.findOneByID(carDTO.getOwner_id()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found");

        var car = new Car();
        BeanUtils.copyProperties(carDTO, car);
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(car));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable(value = "id") UUID id, @RequestBody @Valid CarDTO carDTO) {
        if (carService.findOneByID(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        if (clientService.findOneByID(carDTO.getOwner_id()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found");

        var car = carService.findOneByID(id).get();
        car.setName(carDTO.getName());
        car.setBrand(carDTO.getBrand());
        car.setYear(carDTO.getYear());
        car.setKmTraveled(carDTO.getKmTraveled());

        return ResponseEntity.status(HttpStatus.OK).body(carService.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable(value = "id") UUID id) {
        Optional<Car> optionalCar = carService.findOneByID(id);
        if (optionalCar.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        carService.delete(optionalCar.get());
        return ResponseEntity.status(HttpStatus.OK).body("Car is deleted");
    }
}








