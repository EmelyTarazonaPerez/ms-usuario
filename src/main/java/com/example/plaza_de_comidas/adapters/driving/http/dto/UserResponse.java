package com.example.plaza_de_comidas.adapters.driving.http.dto;

import com.example.plaza_de_comidas.domain.model.Rol;
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
    private String phone;
    private String gmail;
    private Rol idRol;
}
