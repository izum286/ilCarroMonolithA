package com.telran.ilcarro.service.auth;

import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import com.telran.ilcarro.repository.entity.UserRoleEntity;
import com.telran.ilcarro.service.user.AccountCredentials;
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
    public String updatePassword(String userEmail, String newPassword) {
        try {
            UserDetailsEntity current = userDetailsRepository.findById(userEmail)
                    .orElseThrow(()->new NotFoundServiceException(String.format("User %s not found!", userEmail)));
            current.setPassword(encoder.encode(tokenService.decodePassword(newPassword)));
            userDetailsRepository.save(current);
            return userEmail;
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }
}
