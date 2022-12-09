package com.example.garagem_pw3.DTO;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class SaleDTO {
    @NotBlank
    private String date;
    @NotBlank
    private String value;
    @NotBlank
    private String car_id;
    @NotBlank
    private String buyer_id;

    public LocalDate getDate() {
        return LocalDate.parse(date);
    }
    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return BigDecimal.valueOf(Double.parseDouble(value));
    }
    public void setValue(String value) {
        this.value = value;
    }

    public UUID getCar_id() {
        return UUID.fromString(car_id);
    }
    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public UUID getBuyer_id() {
        return UUID.fromString(buyer_id);
    }
    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }
}
