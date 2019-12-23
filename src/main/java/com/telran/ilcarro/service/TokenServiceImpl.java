package com.telran.ilcarro.service;

import org.springframework.stereotype.Service;

import java.util.Base64;
/**
 *
 * TokenService interface implementation
 * @author Konkin Anton
 * @date 19.12.2019
 */

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public AccountCredentials decodeToken(String token) {
        int index = token.indexOf(" ");
        token = token.substring(index + 1);
        byte[] base64 = Base64.getDecoder().decode(token);
        token = new String(base64);
        String[] auth = token.split(":");
        return new AccountCredentials(auth[0], auth[1]);
    }

    @Override
    public String decodePassword(String password) {
        return new String(Base64.getDecoder().decode(password));
    }
}
