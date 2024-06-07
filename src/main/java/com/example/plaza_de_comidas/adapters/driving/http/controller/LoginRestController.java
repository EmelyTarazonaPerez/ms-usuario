package com.example.plaza_de_comidas.adapters.driving.http.controller;

import com.example.plaza_de_comidas.adapters.driving.http.dto.AuthResponse;
import com.example.plaza_de_comidas.adapters.driving.http.dto.LoginRequest;
import com.example.plaza_de_comidas.domain.spi.IJwtPort;
import com.example.plaza_de_comidas.domain.api.IAuthenticationServicePort;
import com.example.plaza_de_comidas.domain.api.IRegisterServicePort;
import com.example.plaza_de_comidas.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginRestController {
    private final IRegisterServicePort registerServicePort;
    private final IAuthenticationServicePort authenticationServicePort;
    private final IJwtPort jwtService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest loginRequest) {

        boolean isAuth = authenticationServicePort.authenticate(loginRequest.getGmail(), loginRequest.getPassword());
        AuthResponse authResponse;
        if (isAuth) {
            User user = registerServicePort.findByGmail(loginRequest.getGmail());
            String token = jwtService.getToken(user);

            authResponse = AuthResponse.builder().auth(token).build();
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
        }

    }
}
