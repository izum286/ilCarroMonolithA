package com.telran.ilcarro.service;

import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;

/**
 * AuthService interface
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface AuthService {
    /**
     * Register new user as UserDetails
     * @param token base64 email + password
     * @return email of registered user
     * @throws ConflictServiceException - if user already registered
     */
    String registration(String token) throws ConflictServiceException;

    /**
     * Password validation
     * @param token - base64 email + password
     * @return email of authenticated user
     * @throws NotFoundServiceException
     * @throws ConflictServiceException
     */

    String validate(String token) throws NotFoundServiceException, ConflictServiceException;

    /**
     * Method update user password
     * @param token - base64 email + password
     * @param newPassword in base64 format
     * @return user email
     * @throws NotFoundServiceException if user not found
     */
    String updatePassword(String token, String newPassword) throws NotFoundServiceException;
}
