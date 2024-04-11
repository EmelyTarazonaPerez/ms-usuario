package com.example.plaza_de_comidas.domain.api.useCase;


import com.example.plaza_de_comidas.domain.api.IUserServicePort;
import com.example.plaza_de_comidas.domain.exception.ExceptionEdadPerson;
import com.example.plaza_de_comidas.domain.model.User;
import com.example.plaza_de_comidas.domain.spi.IUserPersistencePort;

import java.time.LocalDate;

public class UserCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    public UserCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User createAdminAccount(User user) {
        int year = LocalDate.now().getYear() - user.getBirthDate().getYear();
        if (year <= 18) {
            throw new ExceptionEdadPerson("El propetario a crear debe ser mayor de edad");
        }
        return userPersistencePort.save(user);
    }

    @Override
    public User getRolUserById(int id) {
        return userPersistencePort.getRolUserById(id);
    }

    @Override
    public User findByGmail(String gmail) {
        return userPersistencePort.getUserByGmail(gmail);
    }
}
