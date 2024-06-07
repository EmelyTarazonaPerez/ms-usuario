package com.example.plaza_de_comidas.adapters.driven.security.adapter;

import com.example.plaza_de_comidas.domain.spi.IPasswordEncodePort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodeAdapter implements IPasswordEncodePort {
    private PasswordEncoder passwordEncoder;
    public String encodeToPasswordEncoder (String password) {
        return passwordEncoder.encode(password);
    }
}
