package com.example.garagem_pw3.service;

import com.example.garagem_pw3.DAO.SaleDAO;
import com.example.garagem_pw3.domain.Sale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleService {
    private final SaleDAO saleDAO;

    public SaleService(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }

    @Transactional
    public Sale save(Sale sale) {
        return saleDAO.save(sale);
    }

    public List<Sale> findAll() {
        return saleDAO.findAll();
    }

    public Optional<Sale> findOneByID(UUID id) {
        return saleDAO.findById(id);
    }

    @Transactional
    public void delete(Sale sale) {
        saleDAO.delete(sale);
    }
}
