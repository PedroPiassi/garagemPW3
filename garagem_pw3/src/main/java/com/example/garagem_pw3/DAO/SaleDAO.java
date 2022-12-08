package com.example.garagem_pw3.DAO;

import com.example.garagem_pw3.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaleDAO extends JpaRepository<Sale, UUID> {
}
