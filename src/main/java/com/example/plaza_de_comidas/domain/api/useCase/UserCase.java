package com.example.plaza_de_comidas.domain.api.useCase;


import com.example.plaza_de_comidas.domain.api.IUserServicePort;
import com.example.plaza_de_comidas.domain.model.User;
import com.example.plaza_de_comidas.domain.spi.IUserPersistencePort;

public class UserCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    public UserCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User createAdminAccount(User user) {
        return userPersistencePort.save(user);
    }
}
