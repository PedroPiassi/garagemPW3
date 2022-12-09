package com.example.garagem_pw3.controller;

import com.example.garagem_pw3.DTO.SaleDTO;
import com.example.garagem_pw3.domain.Sale;
import com.example.garagem_pw3.service.CarService;
import com.example.garagem_pw3.service.ClientService;
import com.example.garagem_pw3.service.SaleService;
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
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;
    private final ClientService clientService;
    private final CarService carService;

    public SaleController(SaleService saleService, ClientService clientService, CarService carService) {
        this.saleService = saleService;
        this.clientService = clientService;
        this.carService = carService;
    }


    @GetMapping
    public ResponseEntity<List<Sale>> getAllCars() {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneSale(@PathVariable(value = "id") UUID id) {
        if (saleService.findOneByID(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale not found");
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findOneByID(id).get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid SaleDTO saleDTO) {
        var sale = new Sale();
        BeanUtils.copyProperties(saleDTO, sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.save(sale));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSale(@PathVariable(value = "id") UUID id, @RequestBody @Valid SaleDTO saleDTO) {
        if (saleService.findOneByID(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale not found");
        if (carService.findOneByID(saleDTO.getCar_id()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        if (clientService.findOneByID(saleDTO.getBuyer_id()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");

        var sale = saleService.findOneByID(id).get();
        sale.setDate(saleDTO.getDate());
        sale.setValue(saleDTO.getValue());
        sale.setCar(carService.findOneByID(saleDTO.getCar_id()).get());
        sale.setBuyer(clientService.findOneByID(saleDTO.getBuyer_id()).get());

        return ResponseEntity.status(HttpStatus.OK).body(saleService.save(sale));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSale(@PathVariable(value = "id") UUID id) {
        if (saleService.findOneByID(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale not found");
        saleService.delete(saleService.findOneByID(id).get());
        return ResponseEntity.status(HttpStatus.OK).body("Sale is deleted");
    }
}
