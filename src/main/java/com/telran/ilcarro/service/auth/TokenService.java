package com.telran.ilcarro.service.auth;

import com.telran.ilcarro.service.user.AccountCredentials;


public interface TokenService {
    AccountCredentials decodeToken(String token);
    String decodePassword(String password);
}
