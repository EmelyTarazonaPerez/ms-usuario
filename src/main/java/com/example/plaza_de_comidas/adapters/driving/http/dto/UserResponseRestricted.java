package com.example.plaza_de_comidas.adapters.driving.http.dto;

import com.example.plaza_de_comidas.domain.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserResponseRestricted {
    private int idUser;
    private Rol idRol;
}
