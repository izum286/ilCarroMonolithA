package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 *
 * CRUD UserEntityRepository interface
 * @author Konkin Anton
 * 19.12.2019
 */
public interface UserEntityRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> getUserEntityByOwnCarsContains(String carSerialNumber);
}
