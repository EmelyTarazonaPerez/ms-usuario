package com.example.plaza_de_comidas.adapters.driving.http.controller;

import com.example.plaza_de_comidas.adapters.driving.http.JwtService.JwtService;
import com.example.plaza_de_comidas.adapters.driving.http.dto.AddUserRequest;
import com.example.plaza_de_comidas.adapters.driving.http.dto.AuthResponse;
import com.example.plaza_de_comidas.adapters.driving.http.dto.LoginRequest;
import com.example.plaza_de_comidas.adapters.driving.http.handler.InvalidAutorization;
import com.example.plaza_de_comidas.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.plaza_de_comidas.domain.api.IUserServicePort;
import com.example.plaza_de_comidas.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final JwtService jwtService;

    @PostMapping("/create/employee")
    public ResponseEntity<AuthResponse> employeeRecord (@Valid @RequestBody AddUserRequest addUserRequest){
        User user = userRequestMapper.toUser(addUserRequest);
        userServicePort.createEmployeeAccount(user);
        AuthResponse authResponse = AuthResponse.builder().auth(jwtService.getToken(user)).build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/create/owner")
    public ResponseEntity<AuthResponse> ownerRegistration (@Valid @RequestBody AddUserRequest addUserRequest){
        User user = userRequestMapper.toUser(addUserRequest);
        userServicePort.createAdminAccount(user);
        AuthResponse authResponse = AuthResponse.builder().auth(jwtService.getToken(user)).build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/create/customer")
    public ResponseEntity<User> customeRegistration (@Valid @RequestBody AddUserRequest addUserRequest){
        User user = userRequestMapper.toUser(addUserRequest);
        final User costumerAccount = userServicePort.createCostumerAccount(user);
        return new ResponseEntity<>(costumerAccount, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<User> getUserById(@RequestParam() int id){
        return new ResponseEntity<>(userServicePort.getRolUserById(id), HttpStatus.OK);
    }

}
