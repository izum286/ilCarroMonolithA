package com.telran.ilcarro.service;

import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;

/**
 * AuthService interface
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface AuthService {
    String registration(String token);
}
