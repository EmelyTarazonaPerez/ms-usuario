package com.example.plaza_de_comidas.adapters.driving.http.handler;

public class InvalidAutorization extends RuntimeException{
    public InvalidAutorization(String message) {
        super(message);
    }
}
