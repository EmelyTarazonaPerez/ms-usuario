package com.example.plaza_de_comidas.adapters.driving.http.controller;

import com.example.plaza_de_comidas.config.Auth.ConfigJwt.JwtService;
import com.example.plaza_de_comidas.adapters.driving.http.dto.AddUserRequest;
import com.example.plaza_de_comidas.adapters.driving.http.dto.AuthResponse;
import com.example.plaza_de_comidas.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.plaza_de_comidas.domain.api.IUserServicePort;
import com.example.plaza_de_comidas.domain.model.Rol;
import com.example.plaza_de_comidas.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class RegisterRestControllerTest {

    @Mock
    IUserServicePort userServicePort;
    @Mock
    IUserRequestMapper userRequestMapper;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    RegisterRestController userRestController;
    @Mock
    private User empleado;

    private MockMvc mockMcv;
    @BeforeEach
    void setUp() {
        mockMcv = MockMvcBuilders.standaloneSetup(userRestController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        empleado = new User(
                1,
                "propietario",
                "propietario1",
                "1090506050",
                "+573104922805",
                LocalDate.of(1997,04,11),
                "propietario@gmail.com",
                new Rol(3, "empleado", "rol"),
                "passwordEncryptada"

        );
    }

    @Test
    @DisplayName("retornar status 200 al registrar un propietario correctamente")
    void ownerRegistration_SuccessfulRegistration() {
        // Arrange
        AddUserRequest addUserRequest = new AddUserRequest(  "propietario",
                "propietario1",
                "1090506050",
                "+573104922805",
                LocalDate.of(1997,04,11),
                "propietario@gmail.com",
                "passwordEncryptada",
                new Rol(3, "empleado", "rol")
        ); // Supongamos que aquí inicializas el objeto con datos válidos

        User propietario = empleado;
        propietario.getIdRol().setIdRol(2);
        when(userRequestMapper.toUser(addUserRequest)).thenReturn(propietario);

        String token = "mockToken";
        when(jwtService.getToken(propietario)).thenReturn(token);

        // Act
        ResponseEntity<AuthResponse> response = userRestController.ownerRegistration(addUserRequest);

        // Assert
        verify(userServicePort).createOwnerAccount(propietario);
        verify(jwtService).getToken(propietario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getAuth());
    }

    @Test
    void testEmployeeRecord() {
        // Arrange
        AddUserRequest addUserRequest = new AddUserRequest(
                "propietario",
                "propietario1",
                "1090506050",
                "+573104922805",
                LocalDate.of(1997,04,11),
                "propietario@gmail.com",
                "passwordEncryptada",
                new Rol(3, "empleado", "rol")
        ); // Supongamos que aquí inicializas el objeto con datos válidos

        when(userRequestMapper.toUser(addUserRequest)).thenReturn(empleado);

        String token = "mockToken";
        when(jwtService.getToken(empleado)).thenReturn(token);

        // Act
        ResponseEntity<AuthResponse> response = userRestController.ownerRegistration(addUserRequest);

        // Assert
        verify(userServicePort).createOwnerAccount(empleado);
        verify(jwtService).getToken(empleado);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getAuth());
    }

    @Test
    void customerRegistration() throws Exception {
        empleado.setIdUser(4);
        User customer = empleado;
        AddUserRequest addUserRequest = new AddUserRequest("any",
                "an", "11125", "541", LocalDate.now(), "djsad@gmail.com", "12345",
                new Rol(4, "cliente", "cliente") );

        // Configura el comportamiento del mock para userRequestMapper
        when(userRequestMapper.toUser(any(AddUserRequest.class))).thenReturn(customer);

        // Configura el comportamiento del mock para userServicePort
        doReturn(customer).when(userServicePort).createCostumerAccount(customer);

        // Act
        ResponseEntity<User> response = userRestController.customerRegistration(addUserRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}