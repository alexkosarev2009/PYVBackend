package org.pyv.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;

public interface JwtService {
    String generateToken(UserDetails user);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails user);
}