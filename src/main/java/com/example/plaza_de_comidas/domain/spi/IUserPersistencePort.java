package com.example.plaza_de_comidas.domain.spi;


import com.example.plaza_de_comidas.domain.model.User;

public interface IUserPersistencePort {
    User save (User user);
}
