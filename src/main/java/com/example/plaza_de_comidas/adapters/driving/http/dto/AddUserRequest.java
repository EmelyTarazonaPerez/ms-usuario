package com.example.plaza_de_comidas.adapters.driving.http.dto;

import com.example.plaza_de_comidas.domain.model.Rol;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AddUserRequest {

    @Valid

    @NotNull
    private String name;
    private String lastName;
    @Pattern(regexp = "^[0-9]+$", message = "Solo se permiten números")
    private String identificationDocument;
    @Size(max=13, message="telefono debe tener maximo 13 caracteres")
    private String phone;
    private LocalDate birthDate;
    @Email(message = "Correo electrónico no válido")
    private String gmail;
    private String password;
    private Rol idRol;
}
