package com.example.garagem_pw3.domain;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "carro")
public class Carro implements Serializable {
    @Serial
    private static final long serialiVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = false, length = 55)
    private String nome;

    @Column(nullable = false, unique = false, length = 55)
    private String marca;

    @Column(nullable = false, unique = false)
    private Integer ano;

    @Column(nullable = false, unique = false)
    private Double kmRodados;

    @Column(nullable = false, unique = false, length = 55)
    private String placa;

    @Column(nullable = false, unique = false)
    private Double valor;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getKmRodados() {
        return kmRodados;
    }

    public void setKmRodados(Double kmRodados) {
        this.kmRodados = kmRodados;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
