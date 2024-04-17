package com.example.plaza_de_comidas.domain.api;

import com.example.plaza_de_comidas.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface IJwtServicePort {

    public String getToken(User user);

    public String getUsernameFromToken(String token);

    public boolean isTokenValid(String token, UserDetails userDetails);

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver);

    public boolean validateToken(String token);

    public String extractRole(String token);

    public  int getUserIdFromToken(String token);
}
