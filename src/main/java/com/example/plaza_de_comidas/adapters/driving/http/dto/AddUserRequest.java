package com.example.plaza_de_comidas.adapters.driving.http.dto;

import com.example.plaza_de_comidas.domain.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AddUserRequest {
    private String name;
    private String lastName;
    private String identificationDocument;
    private String phone;
    private LocalDate birthDate;
    private String gmail;
    private String password;
    private Rol idRol;

}
