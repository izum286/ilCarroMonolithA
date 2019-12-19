package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.UserEntity;

/**
 *
 * CRUD UserEntityRepository interface
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface UserEntityRepository {
    UserEntity getUserByEmail(String email);
    boolean addUser(UserEntity entity);
    UserEntity updateUser(UserEntity entity);
    boolean deleteUser(String email);
}
