package com.telran.ilcarro.service;

import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import com.telran.ilcarro.repository.entity.UserRoleEntity;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    UserDetailsRepository userDetailsRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public String registration(String token) {
        AccountCredentials account = tokenService.decodeToken(token);
        if (userDetailsRepository.existsById(account.email)) {
            throw new ConflictServiceException(String.format("User %s already exist", account.email));
        }
        UserDetailsEntity entity = UserDetailsEntity.builder()
                .email(account.email)
                .password(encoder.encode(account.password))
                .roles(List.of(UserRoleEntity.builder()
                        .role("ROLE_USER")
                        .build()))
                .build();
        userDetailsRepository.save(entity);
        return account.email;
    }

    @Override
    public String updatePassword(String token, String newPassword) {
        AccountCredentials account = tokenService.decodeToken(token);
        try {
            UserDetailsEntity current = userDetailsRepository.findById(account.email)
                    .orElseThrow(()->new NotFoundServiceException(String.format("User %s not found!", account.email)));
            current.setPassword(encoder.encode(tokenService.decodePassword(newPassword)));
            userDetailsRepository.save(current);
            return account.email;
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    @Override
    public boolean validate(String token) {
        AccountCredentials account = tokenService.decodeToken(token);
        try {
            Optional<UserDetailsEntity> current = userDetailsRepository.findById(account.email);
            if (current.isEmpty()) {
                throw new NotFoundServiceException(String.format("User %s not found!", account.email));
            }
            String oldPAss = current.get().getPassword();
            boolean isValid = encoder.matches(account.password, oldPAss);

            if (!isValid) {
                throw new ConflictServiceException(String.format("Incorrect password for user %s !", account.email));
            }
            return true;
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }
}
