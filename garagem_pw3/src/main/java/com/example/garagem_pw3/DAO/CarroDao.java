package com.example.garagem_pw3.DAO;

import com.example.garagem_pw3.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CarroDao extends JpaRepository<Carro, UUID> {
    Boolean existsByPlaca(String Placa);
}
