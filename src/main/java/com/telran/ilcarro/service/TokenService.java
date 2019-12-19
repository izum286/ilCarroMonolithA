package com.telran.ilcarro.service;

public interface TokenService {
    AccountCredentials decodeToken(String token);
}
