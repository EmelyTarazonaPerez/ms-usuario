package com.example.plaza_de_comidas.adapters.driving.http.controller;

import com.example.plaza_de_comidas.adapters.driving.http.dto.AddUserRequest;
import com.example.plaza_de_comidas.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.plaza_de_comidas.domain.api.IUserServicePort;
import com.example.plaza_de_comidas.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    @PostMapping("/create")
    public ResponseEntity<User> save(@RequestBody AddUserRequest addUserRequest){
        User user = userRequestMapper.toUser(addUserRequest);
        return new ResponseEntity<>(userServicePort.createAdminAccount(user), HttpStatus.OK);
    }
}
