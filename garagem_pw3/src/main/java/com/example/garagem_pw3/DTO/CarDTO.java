package com.example.garagem_pw3.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

public class CarDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String brand;
    @NotBlank
    private String year;
    @NotBlank
    private String kmTraveled;
    @NotBlank
    @Size(max = 7)
    private String plate;
    @NotBlank
    private String price;
    @NotBlank
    private String owner_id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return Integer.parseInt(year);
    }
    public void setYear(String year) {
        this.year = year;
    }

    public Double getKmTraveled() {
        return Double.parseDouble(kmTraveled);
    }
    public void setKmTraveled(String kmTraveled) {
        this.kmTraveled = kmTraveled;
    }

    public BigDecimal getPrice() {
        return BigDecimal.valueOf(Double.parseDouble(price));
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public UUID getOwner_id() {
        return UUID.fromString(owner_id);
    }
    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }
}
