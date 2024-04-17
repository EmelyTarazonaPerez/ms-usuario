package com.example.plaza_de_comidas.adapters.driving.http.controller;

import com.example.plaza_de_comidas.config.Auth.ConfigJwt.JwtService;
import com.example.plaza_de_comidas.adapters.driving.http.dto.*;
import com.example.plaza_de_comidas.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.plaza_de_comidas.adapters.driving.http.mapper.IUserResponseMapper;
import com.example.plaza_de_comidas.domain.api.IUserServicePort;
import com.example.plaza_de_comidas.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegisterRestController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

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
        userServicePort.createOwnerAccount(user);
        AuthResponse authResponse = AuthResponse.builder().auth(jwtService.getToken(user)).build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/create/customer")
    public ResponseEntity<User> customerRegistration (@Valid @RequestBody AddUserRequest addUserRequest){
        User user = userRequestMapper.toUser(addUserRequest);
        final User costumerAccount = userServicePort.createCostumerAccount(user);
        return new ResponseEntity<>(costumerAccount, HttpStatus.OK);
    }

    @GetMapping("/get/user")
    public ResponseEntity<UserResponse> getUserById(@RequestParam() int id){
        return new ResponseEntity<>(userResponseMapper.toUserResponse(userServicePort.getRolUserById(id)), HttpStatus.OK);
    }

}
