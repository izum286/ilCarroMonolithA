package com.telran.ilcarro.service.auth;

import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;

public interface AuthService {
    /**
     * Register new user as UserDetails
     * @param token base64 email + password
     * @return email of registered user
     * @throws ConflictServiceException - if user already registered
     */
    String registration(String token) throws ConflictServiceException;

    /**
     * Method update user password
     * @param userEmail - email
     * @param newPassword in base64 format
     * @return user email
     * @throws NotFoundServiceException if user not found
     */
    String updatePassword(String userEmail, String newPassword) throws NotFoundServiceException;
}
