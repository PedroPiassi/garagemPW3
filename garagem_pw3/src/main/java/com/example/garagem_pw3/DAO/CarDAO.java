package com.example.garagem_pw3.DAO;

import com.example.garagem_pw3.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CarDAO extends JpaRepository<Car, UUID> {
    Boolean existsByPlate(String Plate);

    List<Car> findByOwnerId(UUID owner_id);
}
