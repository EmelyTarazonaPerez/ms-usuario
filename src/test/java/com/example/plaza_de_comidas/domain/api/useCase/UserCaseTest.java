package com.example.plaza_de_comidas.domain.api.useCase;

import com.example.plaza_de_comidas.domain.exception.ExceptionInsertUser;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCaseTest {

    @Mock
    IUserPersistencePort userPersistencePort;
    @InjectMocks
    RegisterService userCase;

    private User userInput;

    @BeforeEach
    void setUp() {
        userInput = new User(
                1,
                "test",
                "test",
                "1090506050",
                "+573104922805",
                LocalDate.of(1997, 04, 11),
                "propietario@gmail.com",
                new Rol(3, "test", "rol"),
                "passwordEncryptada"

        );
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
                LocalDate.of(1997, 04, 11),
                "propietario@gmail.com",
                new Rol(2, "propietario", "rol"),
                "passwordEncryptada"

        );
        when(userPersistencePort.save(propietario)).thenReturn(propietario);
        final User result = userCase.createOwnerAccount(propietario);

        Assertions.assertEquals(propietario, result);
    }

    @Test
    void testCreateEmployeeAccount_ValidUser() {
        // Arrange
        userInput.setBirthDate(LocalDate.of(1990, 1, 1)); // Supongamos que el usuario tiene más de 18 años
        userInput.setIdRol(new Rol(3, "test", "test")); // Supongamos que el ID de rol es 3

        when(userPersistencePort.save(userInput)).thenReturn(userInput);
        // Act
        userCase.createEmployeeAccount(userInput);

        // Assert
        verify(userPersistencePort, times(1)).save(userInput);
    }

    @Test
    void testCreateEmployeeAccount_UnderageUser() {
        // Arrange
        userInput.setBirthDate(LocalDate.now()); // Supongamos que el usuario tiene menos de 18 años
        userInput.setIdRol(new Rol(3, "test", "test")); // Supongamos que el ID de rol es 3

        Exception exception = assertThrows(ExceptionInsertUser.class, () -> {
            userCase.createEmployeeAccount(userInput);
        });

        assertEquals("El empleado a crear debe ser mayor de edad", exception.getMessage());
        verify(userPersistencePort, never()).save(any(User.class));
    }

    @Test
    void testCreateEmployeeAccount_InvalidRoleId() {
        // Arrange
        userInput.setBirthDate(LocalDate.of(1990, 1, 1)); // Supongamos que el usuario tiene más de 18 años
        userInput.setIdRol(new Rol(1, "test", "test")); // Supongamos que el ID de rol es diferente de 3


        // Act & Assert
        Exception exception = assertThrows(ExceptionInsertUser.class, () -> {
            userCase.createEmployeeAccount(userInput);
        });

        assertEquals("Warning: Error idRol", exception.getMessage());
        verify(userPersistencePort, never()).save(any(User.class));
    }

    @Test
    void createCostumerAccount() {
        User customer = new User(
                1,
                "customer",
                "customer",
                "1090506050",
                "+573104922805",
                LocalDate.of(1997, 04, 11),
                "customer@gmail.com",
                new Rol(4, "customer", "rol"),
                "passwordEncryptada"

        );
        when(userPersistencePort.save(customer)).thenReturn(customer);
        final User result = userCase.createCostumerAccount(customer);

        Assertions.assertEquals(customer, result);
    }

    @Test
    void createCostumerAccount_InvalidRoleId() {
        User customer = new User(
                1,
                "customer",
                "customer",
                "1090506050",
                "+573104922805",
                LocalDate.of(1997, 04, 11),
                "customer@gmail.com",
                new Rol(2, "customer", "rol"),
                "passwordEncryptada"

        );
        // Act & Assert
        Exception exception = assertThrows(ExceptionInsertUser.class, () -> {
            userCase.createEmployeeAccount(customer);
        });

        assertEquals("Warning: Error idRol", exception.getMessage());
        verify(userPersistencePort, never()).save(any(User.class));
    }

}