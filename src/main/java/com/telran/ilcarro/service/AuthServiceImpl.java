package com.telran.ilcarro.service;

import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import com.telran.ilcarro.repository.entity.UserRoleEntity;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * AuthService interface implementation
 * @author Konkin Anton
 * @date 19.12.2019
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserDetailsRepository userRepo;

    @Autowired
    PasswordEncoder encoder;

    /**
     * Register User to AuthService and save to UserDetailsRepository
     * @param token
     * @return user email
     * @throws ConflictServiceException
     */
    @Override
    public String registration(String token) {
        AccountCredentials account = tokenService.decodeToken(token);
        if (userRepo.existsById(account.email)) {
            throw new ConflictServiceException(String.format("User %s already exist", account.email));
        }
        UserDetailsEntity entity = UserDetailsEntity.builder()
                .email(account.email)
                .password(encoder.encode(account.password))
                .roles(List.of(UserRoleEntity.builder()
                        .role("ROLE_USER")
                        .build()))
                .build();
        userRepo.save(entity);
        return account.email;
    }
    /**
     * Update User password in AuthService and save to UserDetailsRepository
     * @param token
     * @return user email
     * @throws NotFoundServiceException
     */
    @Override
    public String updatePassword(String token, String newPassword) {
        AccountCredentials account = tokenService.decodeToken(token);
        try {
            UserDetailsEntity current = userRepo.findById(account.email).get();
            current.setPassword(encoder.encode(tokenService.decodePassword(newPassword)));
            userRepo.save(current);
            return account.email;
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }
}
