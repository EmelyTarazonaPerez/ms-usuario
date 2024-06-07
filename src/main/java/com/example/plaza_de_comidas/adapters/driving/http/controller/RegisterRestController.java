package com.example.plaza_de_comidas.adapters.driving.http.controller;

import com.example.plaza_de_comidas.adapters.driven.security.adapter.JwtAdapter;
import com.example.plaza_de_comidas.adapters.driving.http.dto.*;
import com.example.plaza_de_comidas.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.plaza_de_comidas.adapters.driving.http.mapper.IUserResponseMapper;
import com.example.plaza_de_comidas.domain.api.IRegisterServicePort;
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
    private final IRegisterServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    private final JwtAdapter jwtAdapter;

    @PostMapping("/create/employee")
    public ResponseEntity<AuthResponse> employeeRecord (@Valid @RequestBody AddUserRequest addUserRequest){
        User user = userRequestMapper.toUser(addUserRequest);
        userServicePort.createEmployeeAccount(user);
        AuthResponse authResponse = AuthResponse.builder().auth(jwtAdapter.getToken(user)).build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/create/owner")
    public ResponseEntity<AuthResponse> ownerRegistration (@Valid @RequestBody AddUserRequest addUserRequest){
        User user = userRequestMapper.toUser(addUserRequest);
        userServicePort.createOwnerAccount(user);
        AuthResponse authResponse = AuthResponse.builder().auth(jwtAdapter.getToken(user)).build();
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
