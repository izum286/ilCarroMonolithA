package com.telran.ilcarro.service.user;

import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import com.telran.ilcarro.repository.entity.UserRoleEntity;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.NoSuchElementException;
/**
 *
 * UserDetailsService custom implemetation for AuthService
 * @see UserDetailsRepository
 * @see UserDetailsEntity
 * @author Konkin Anton
 * 19.12.2019
 */
public class CustomUserDetailsService implements UserDetailsService {
    UserDetailsRepository repository;

    public CustomUserDetailsService(UserDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        try {
            UserDetailsEntity entity = repository.findById(user).orElseThrow();
            String[] roles = entity.getRoles().stream()
                    .map(UserRoleEntity::getRole)
                    .toArray(String[]::new);
            return User.builder()
                    .username(entity.getEmail())
                    .password(entity.getPassword())
                    .authorities(AuthorityUtils.createAuthorityList(roles))
                    .build();
        } catch (NoSuchElementException ex) {
            throw new NotFoundServiceException(String.format("User %s doesn't exist!", user));
        }
    }
}
