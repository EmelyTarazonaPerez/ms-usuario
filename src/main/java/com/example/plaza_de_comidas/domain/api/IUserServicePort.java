package com.example.plaza_de_comidas.domain.api;


import com.example.plaza_de_comidas.domain.model.User;

public interface IUserServicePort {
    User createOwnerAccount(User user);
    User getRolUserById(int id);
    User findByGmail(String gmail);
    void createEmployeeAccount(User user);
    User createCostumerAccount(User user);
}
