package com.example.garagem_pw3.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cars")
public class Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String brand;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private Double kmTraveled;
    @Column(nullable = false, length = 7)
    private String plate;
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(12,2) DEFAULT 0.00")
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Client owner;

    public Car() {
    }
    public Car(UUID id, String name, String brand, Integer year, Double kmTraveled, String plate, BigDecimal price, Client owner) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.kmTraveled = kmTraveled;
        this.plate = plate;
        this.price = price;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getKmTraveled() {
        return kmTraveled;
    }
    public void setKmTraveled(Double kmTraveled) {
        this.kmTraveled = kmTraveled;
    }

    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Client getOwner() {
        return owner;
    }
    public void setOwner(Client owner) {
        this.owner = owner;
    }
}
