package com.example.garagem_pw3.service;

import com.example.garagem_pw3.DAO.CarroDao;
import com.example.garagem_pw3.domain.Carro;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarroService {
    private final CarroDao carroDao;

    public CarroService(CarroDao carroDao) {
        this.carroDao = carroDao;
    }

    @Transactional
    public Carro save(Carro carro) {
        return carroDao.save(carro);
    }

    public List<Carro> findAll() {
        return carroDao.findAll();
    }

    public Optional<Carro> findById(UUID id) {
        return carroDao.findById(id);
    }

    @Transactional
    public void delete(Carro carro) {
        carroDao.delete(carro);
    }

    public boolean existsByPlaca(String placa) {
        return carroDao.existsByPlaca(placa);
    }
}
