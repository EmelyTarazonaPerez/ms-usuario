package com.example.plaza_de_comidas.adapters.driving.http.controller;

import com.example.plaza_de_comidas.adapters.driving.http.dto.AddUserRequest;
import com.example.plaza_de_comidas.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.plaza_de_comidas.domain.api.IUserServicePort;
import com.example.plaza_de_comidas.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Mock
    IUserServicePort userServicePort;
    @Mock
    IUserRequestMapper userRequestMapper;
    @InjectMocks
    UserRestController userRestController;
    private MockMvc mockMcv;
    @BeforeEach
    void setUp() {
        mockMcv = MockMvcBuilders.standaloneSetup(userRestController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    @DisplayName("retornar status 200 al registrar un propietario correctamente")
    void save() throws Exception {
        String propietarioJSON = "{\n" +
                "    \"name\": \"n\",\n" +
                "    \"lastName\": \"Propietario\",\n" +
                "    \"identificationDocument\":\"1090506050\",\n" +
                "    \"phone\": \"3104922805\",\n" +
                "    \"birthDate\": \"1997-04-11\",\n" +
                "    \"gmail\": \"Propietario@gmail.com\",\n" +
                "    \"password\": \"sfasdasda\",\n" +
                "     \"idRol\": {\n" +
                "        \"idRol\": 2,\n" +
                "        \"name\": \"propietario\",\n" +
                "        \"description\": \"sdada\"\n" +
                "    }\n" +
                "    \n" +
                "}";

        when(userServicePort.createAdminAccount(userRequestMapper.toUser(any(AddUserRequest.class))))
                .thenReturn(any(User.class));

        mockMcv.perform(post("/user/create")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(propietarioJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("retornar status 400 al registrar un identificationDocument incorrectamente")
    void saveCcError() throws Exception {
        String propietarioJSON = "{\n" +
                "    \"name\": \"n\",\n" +
                "    \"lastName\": \"Propietario\",\n" +
                "    \"identificationDocument\":\"109050p6050\",\n" +
                "    \"phone\": \"3104922805\",\n" +
                "    \"birthDate\": \"1997-04-11\",\n" +
                "    \"gmail\": \"Propietario@gmail.com\",\n" +
                "    \"password\": \"sfasdasda\",\n" +
                "     \"idRol\": {\n" +
                "        \"idRol\": 2,\n" +
                "        \"name\": \"propietario\",\n" +
                "        \"description\": \"sdada\"\n" +
                "    }\n" +
                "    \n" +
                "}";

        mockMcv.perform(post("/user/create")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(propietarioJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("retornar status 400 al registrar un phone incorrectamente")
    void savePhoneError() throws Exception {
        String propietarioJSON = "{\n" +
                "    \"name\": \"n\",\n" +
                "    \"lastName\": \"Propietario\",\n" +
                "    \"identificationDocument\":\"1090506050\",\n" +
                "    \"phone\": \"+573104922805-----------------------------\",\n" +
                "    \"birthDate\": \"1997-04-11\",\n" +
                "    \"gmail\": \"Propietario@gmail.com\",\n" +
                "    \"password\": \"sfasdasda\",\n" +
                "     \"idRol\": {\n" +
                "        \"idRol\": 2,\n" +
                "        \"name\": \"propietario\",\n" +
                "        \"description\": \"sdada\"\n" +
                "    }\n" +
                "    \n" +
                "}";


        mockMcv.perform(post("/user/create")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(propietarioJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("retornar status 400 al registrar un correo incorrectamente")
    void saveGmailError() throws Exception {
        String propietarioJSON = "{\n" +
                "    \"name\": \"n\",\n" +
                "    \"lastName\": \"Propietario\",\n" +
                "    \"identificationDocument\":\"1090506050\",\n" +
                "    \"phone\": \"+573104922805\",\n" +
                "    \"birthDate\": \"1997-04-11\",\n" +
                "    \"gmail\": \"Propietariogmail.com\",\n" +
                "    \"password\": \"sfasdasda\",\n" +
                "     \"idRol\": {\n" +
                "        \"idRol\": 2,\n" +
                "        \"name\": \"propietario\",\n" +
                "        \"description\": \"sdada\"\n" +
                "    }\n" +
                "    \n" +
                "}";


        mockMcv.perform(post("/user/create")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(propietarioJSON))
                .andExpect(status().isBadRequest());
    }
}