package com.example.garagem_pw3.service;

import com.example.garagem_pw3.DAO.ClientDAO;
import com.example.garagem_pw3.domain.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Transactional
    public Client save(Client client) {
        return clientDAO.save(client);
    }

    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    public Optional<Client> findOneByID(UUID id) {
        return clientDAO.findById(id);
    }

    public Optional<Client> findByEmail(String email) {
        return clientDAO.findByEmail(email);
    }

    @Transactional
    public void delete(Client client) {
        clientDAO.delete(client);
    }

    public boolean existsByCpf(String cpf) {
        return clientDAO.existsByCpf(cpf);
    }
}
