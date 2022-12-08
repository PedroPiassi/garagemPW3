package com.example.garagem_pw3.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "sales")
public class Sale implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate date;
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(12,2) DEFAULT 0.00")
    private BigDecimal value;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private Client buyer;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public Client getBuyer() {
        return buyer;
    }
    public void setBuyer(Client buyer) {
        this.buyer = buyer;
    }
}
