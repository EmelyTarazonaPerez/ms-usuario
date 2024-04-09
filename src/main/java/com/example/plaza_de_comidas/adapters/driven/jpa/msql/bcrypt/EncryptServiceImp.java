package com.example.plaza_de_comidas.adapters.driven.jpa.msql.bcrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptServiceImp implements EncryptPassword{
    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean verifyPassword(String originalPassword, String hashPassword) {

        return false;

    }
}
