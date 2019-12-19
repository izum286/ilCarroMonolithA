package com.telran.ilcarro.service;

import java.util.Base64;

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
}
