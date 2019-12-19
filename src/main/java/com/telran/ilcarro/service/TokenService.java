package com.telran.ilcarro.service;
/**
 *
 * TokenService interface
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface TokenService {
    AccountCredentials decodeToken(String token);
}
