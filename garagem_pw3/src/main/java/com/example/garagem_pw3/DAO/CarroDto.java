package com.example.garagem_pw3.DAO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class CarroDto {
    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 7)
    private String placa;

    @NotBlank
    private String marca;

    @NotBlank
    private String ano;

    @NotBlank
    private String kmRodados;

    @NotBlank
    private String valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return Integer.parseInt(ano);
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Double getKmRodados() {
        return Double.parseDouble(kmRodados);
    }

    public void setKmRodados(String kmRodados) {
        this.kmRodados = kmRodados;
    }

    public Double getValor() {
        return Double.parseDouble(valor);
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
