package com.example.garagem_pw3.controller;

import com.example.garagem_pw3.DAO.CarroDto;
import com.example.garagem_pw3.domain.Carro;
import com.example.garagem_pw3.service.CarroService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/carro")
public class CarroController {
    final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid CarroDto carroDto) {
        if (carroService.existsByPlaca(carroDto.getPlaca()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa do carro já cadastrada!");

        var carro = new Carro();
        BeanUtils.copyProperties(carroDto, carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(carroService.save(carro));
    }

    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros() {
        return ResponseEntity.status(HttpStatus.OK).body(carroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCarro(@PathVariable(value = "id") UUID id) {
        Optional<Carro> carroOptional = carroService.findById(id);
        if (!carroOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
        return ResponseEntity.status(HttpStatus.OK).body(carroOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCarro(@PathVariable(value = "id") UUID id) {
        Optional<Carro> carroOptional = carroService.findById(id);
        if (!carroOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
        carroService.delete(carroOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Carro deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCarro(@PathVariable(value = "id") UUID id, @RequestBody @Valid CarroDto carroDto) {
        Optional<Carro> carroOptional = carroService.findById(id);
        if (!carroOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
        var carro = carroOptional.get();
        carro.setNome(carroDto.getNome());
        carro.setMarca(carroDto.getMarca());
        carro.setAno(carroDto.getAno());
        carro.setKmRodados(Double.valueOf(carroDto.getKmRodados()));
        carro.setValor(carroDto.getValor());

        return ResponseEntity.status(HttpStatus.OK).body(carroService.save(carro));
    }
}








