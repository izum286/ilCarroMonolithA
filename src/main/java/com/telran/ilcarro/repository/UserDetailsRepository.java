package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.UserDetailsEntity;

import java.util.Optional;
/**
 *
 * CRUD UserDetailsRepository interface
 * @author Konkin Anton
 * @date 18.12.2019
 */
public interface UserDetailsRepository {
    Optional<UserDetailsEntity> findById(String email);
    boolean existsById(String email);
    boolean save(UserDetailsEntity entity);
}
