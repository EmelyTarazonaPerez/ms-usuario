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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/create/employee")
    public ResponseEntity<AuthResponse> employeeRecord (@Valid @RequestBody AddUserRequest addUserRequest){
        try{
            User user = userRequestMapper.toUser(addUserRequest);
            userServicePort.createEmpleyeeAccount(user);
            AuthResponse authResponse = AuthResponse.builder().auth(jwtService.getToken(user)).build();
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }catch (Exception e){
            throw new InvalidAutorization("No cuenta con los permisos para crear un empleado");
        }
    }

    @PostMapping("/create/owner")
    public ResponseEntity<AuthResponse> ownerRegistration (@Valid @RequestBody AddUserRequest addUserRequest){
        User user = userRequestMapper.toUser(addUserRequest);
        userServicePort.createAdminAccount(user);
        AuthResponse authResponse = AuthResponse.builder().auth(jwtService.getToken(user)).build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getGmail(), loginRequest.getPassword()));

        User user = userServicePort.findByGmail(loginRequest.getGmail());
        String token = jwtService.getToken(user);

        AuthResponse authResponse = AuthResponse.builder().auth(token).build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<User> getUserById(@RequestParam() int id){
        return new ResponseEntity<>(userServicePort.getRolUserById(id), HttpStatus.OK);
    }

}
