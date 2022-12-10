package com.example.garagem_pw3.controller;

import com.example.garagem_pw3.DTO.ClientDTO;
import com.example.garagem_pw3.DTO.LoginDTO;
import com.example.garagem_pw3.domain.Client;
import com.example.garagem_pw3.service.CarService;
import com.example.garagem_pw3.service.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/client")
public class ClientController {
     final ClientService clientService;
    final CarService carService;

    public ClientController (ClientService clientService, CarService carService) {
        this.clientService = clientService;
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id) {
        if (clientService.findOneByID(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findOneByID(id).get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ClientDTO clientDTO) {
        if (clientService.findByEmail(clientDTO.getEmail()).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        if (clientService.existsByCpf(clientDTO.getCpf()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF already exists");

        var client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> getOneByEmail(@RequestBody @Valid LoginDTO loginDTO) {
        if (clientService.findByEmail(loginDTO.getEmail()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");

        var client = clientService.findByEmail(loginDTO.getEmail()).get();

        if (loginDTO.getPassword().equals(client.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).body(client);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid password");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientDTO clientDTO) {
        if (clientService.findOneByID(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");

        var client = clientService.findOneByID(id).get();
        client.setId(id);
        client.setName(clientDTO.getName());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        client.setPassword(clientDTO.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id) {
        if (clientService.findOneByID(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        clientService.delete(clientService.findOneByID(id).get());
        return ResponseEntity.status(HttpStatus.OK).body("Client is deleted");
    }
}
