package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
/**
 *
 * CRUD UserDetailsRepository interface
 * @author Konkin Anton
 * @date 18.12.2019
 */
public interface UserDetailsRepository extends MongoRepository<UserDetailsEntity, String> {
//    Optional<UserDetailsEntity> findById(String email);
//    boolean existsById(String email);
//    save(UserDetailsEntity entity);
}
