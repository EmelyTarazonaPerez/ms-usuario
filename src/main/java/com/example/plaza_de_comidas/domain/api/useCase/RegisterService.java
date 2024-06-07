package com.example.plaza_de_comidas.domain.api.useCase;


import com.example.plaza_de_comidas.domain.api.IRegisterServicePort;
import com.example.plaza_de_comidas.domain.exception.ExceptionInsertUser;
import com.example.plaza_de_comidas.domain.model.User;
import com.example.plaza_de_comidas.domain.spi.IPasswordEncodePort;
import com.example.plaza_de_comidas.domain.spi.IUserPersistencePort;

import java.time.LocalDate;

public class RegisterService implements IRegisterServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncodePort passwordEncodePort;

    public RegisterService(IUserPersistencePort userPersistencePort, IPasswordEncodePort passwordEncodePort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncodePort = passwordEncodePort;
    }

    @Override
    public User createOwnerAccount(User user) {
        int year = LocalDate.now().getYear() - user.getBirthDate().getYear();
        if (year <= 18) {
            throw new ExceptionInsertUser("El propetario a crear debe ser mayor de edad");
        }
        if (user.getIdRol().getIdRol() != 2) {
            throw new ExceptionInsertUser("Warning: Error idRol");
        }
        passwordEncodePort.encodeToPasswordEncoder(user.getPassword());
        return userPersistencePort.save(user);
    }

    @Override
    public void createEmployeeAccount(User user) {
        int year = LocalDate.now().getYear() - user.getBirthDate().getYear();
        if (year <= 18) {
            throw new ExceptionInsertUser("El empleado a crear debe ser mayor de edad");
        }
        if (user.getIdRol().getIdRol() != 3) {
           throw new ExceptionInsertUser("Warning: Error idRol");
        }
        passwordEncodePort.encodeToPasswordEncoder(user.getPassword());
        userPersistencePort.save(user);
    }

    @Override
    public User createCostumerAccount(User user) {
        if (user.getIdRol().getIdRol() != 4) {
            throw new ExceptionInsertUser("Warning: Error idRol");
        }
        passwordEncodePort.encodeToPasswordEncoder(user.getPassword());
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
