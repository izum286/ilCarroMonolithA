package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookedPeriodsRepository extends MongoRepository<BookedPeriodEntity, String> {
}
