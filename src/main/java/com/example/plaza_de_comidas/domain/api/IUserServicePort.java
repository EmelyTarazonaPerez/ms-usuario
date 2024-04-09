package com.example.plaza_de_comidas.domain.api;


import com.example.plaza_de_comidas.domain.model.User;

public interface IUserServicePort {
     User createAdminAccount(User user);
}
