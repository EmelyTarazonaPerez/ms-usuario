package com.example.plaza_de_comidas.adapters.driving.http.controller;

import com.example.plaza_de_comidas.adapters.driving.http.JwtService.JwtService;
import com.example.plaza_de_comidas.adapters.driving.http.dto.AuthResponse;
import com.example.plaza_de_comidas.adapters.driving.http.dto.LoginRequest;
import com.example.plaza_de_comidas.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.plaza_de_comidas.domain.api.IUserServicePort;
import com.example.plaza_de_comidas.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginRestController {
    private final IUserServicePort userServicePort;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getGmail(), loginRequest.getPassword()));

        User user = userServicePort.findByGmail(loginRequest.getGmail());
        String token = jwtService.getToken(user);

        AuthResponse authResponse = AuthResponse.builder().auth(token).build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
