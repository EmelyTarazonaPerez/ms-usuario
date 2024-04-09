package com.example.plaza_de_comidas.adapters.driven.jpa.msql.bcrypt;

public interface EncryptPassword {
    String encryptPassword(String password);
    boolean verifyPassword(String originalPassword, String hashPassword);
}
