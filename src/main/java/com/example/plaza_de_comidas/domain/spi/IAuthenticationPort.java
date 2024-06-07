package com.example.plaza_de_comidas.domain.spi;

import org.springframework.security.core.Authentication;

public interface IAuthenticationPort {
    boolean authentication (String gmail, String password);
}
