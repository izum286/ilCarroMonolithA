package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * CRUD UserEntityRepository interface
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface UserEntityRepository extends MongoRepository<UserEntity, String> {
//    UserEntity getUserByEmail(String email);
//    UserEntity addUser(UserEntity entity);
//    UserEntity updateUser(UserEntity entity);
//    boolean deleteUser(String email);
}
