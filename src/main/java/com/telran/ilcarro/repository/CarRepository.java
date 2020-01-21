package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author izum286
 */
public interface CarRepository extends MongoRepository<FullCarEntity, String>, CarRepositoryCustom {
    /**
     * @author izum286
     * @param circle circle for search
     * @param pageable pageable param
     * @return Page<FullCarEntity>
     * status READY
     */
    Page<FullCarEntity> findAllByPickUpPlaceWithin(Circle circle, Pageable pageable);

    List<FullCarEntity> findAllByOwnerEmail(String ownerEmail);

}
