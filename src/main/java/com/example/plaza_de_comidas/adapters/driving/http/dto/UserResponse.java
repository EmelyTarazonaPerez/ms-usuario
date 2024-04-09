package com.example.plaza_de_comidas.adapters.driving.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserResponse {
    private int idUser;
    private String name;
    private String lastName;
    private String identificationDocument;
    private String phone;
    private LocalDate birthDate;
    private String gmail;
    private int idRol;
    private String clave;
}
