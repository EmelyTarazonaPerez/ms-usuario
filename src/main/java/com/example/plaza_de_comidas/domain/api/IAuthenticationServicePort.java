package com.example.plaza_de_comidas.domain.api;

public interface IAuthenticationServicePort {
    boolean authenticate(String gmail, String password);
}
