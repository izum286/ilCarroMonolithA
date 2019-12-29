package com.telran.ilcarro.service.auth;

import com.telran.ilcarro.service.user.AccountCredentials;

/**
 *
 * TokenService interface
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface TokenService {
    AccountCredentials decodeToken(String token);
    String decodePassword(String password);
}
