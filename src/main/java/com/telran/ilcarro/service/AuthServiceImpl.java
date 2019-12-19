package com.telran.ilcarro.service;

import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import com.telran.ilcarro.repository.entity.UserRoleEntity;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserDetailsRepository userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void registration(String email, String password, String token) {
        if (userRepo.existsById(email)) {
            throw new ConflictServiceException(String.format("User %s already exist", email));
        }
        UserDetailsEntity entity = UserDetailsEntity.builder()
                .email(email)
                .password(encoder.encode(password))
                .roles(List.of(UserRoleEntity.builder()
                        .role("ROLE_USER")
                        .build()))
                .build();
        userRepo.save(entity);
    }
}
