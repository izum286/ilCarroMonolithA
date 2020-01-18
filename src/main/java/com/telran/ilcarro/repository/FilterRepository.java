package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.FilterNodeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *  filter repository
 * @author izum286
 */
@Repository
public interface FilterRepository extends MongoRepository<FilterNodeEntity, String> {
}
