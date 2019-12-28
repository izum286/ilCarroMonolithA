package com.telran.ilcarro.service;

import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;

/**
 * AuthService interface
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface AuthService {
    String registration(String token) throws ConflictServiceException;
    String updatePassword(String token, String newPassword) throws NotFoundServiceException;
}
