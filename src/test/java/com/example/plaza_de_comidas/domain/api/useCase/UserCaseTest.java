package com.example.plaza_de_comidas.domain.api.useCase;

import com.example.plaza_de_comidas.domain.model.Rol;
import com.example.plaza_de_comidas.domain.model.User;
import com.example.plaza_de_comidas.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserCaseTest {

    @Mock
    IUserPersistencePort userPersistencePort;
    @InjectMocks
    UserCase userCase;
    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("crear una cuenta proppetario correctamente")
    void createAdminAccount() {
        User propietario = new User(
                1,
                "propietario",
                "propietario1",
                "1090506050",
                "+573104922805",
                LocalDate.of(1997,04,11),
                "propietario@gmail.com",
                new Rol(2, "propietario", "rol"),
                "passwordEncryptada"

        );
        when(userPersistencePort.save(propietario)).thenReturn(propietario);
        final User result = userCase.createAdminAccount(propietario);

        Assertions.assertEquals(propietario, result);
    }
}