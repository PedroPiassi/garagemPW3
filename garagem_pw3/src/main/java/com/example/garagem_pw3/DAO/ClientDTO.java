package com.example.garagem_pw3.DAO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClientDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    @Size(max = 14)
    private String cpf;
    @NotBlank
    @Size(max = 15)
    private String phone;
    @NotBlank
    private String address;
    @NotBlank
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
