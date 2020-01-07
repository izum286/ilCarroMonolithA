package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 *
 * CRUD UserDetailsRepository interface
 * @author Konkin Anton
 * 18.12.2019
 */
public interface UserDetailsRepository extends MongoRepository<UserDetailsEntity, String> {
}
