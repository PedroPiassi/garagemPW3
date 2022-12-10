package com.example.garagem_pw3.DAO;

import com.example.garagem_pw3.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientDAO extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(String email);

    boolean existsByCpf(String cpf);
}
