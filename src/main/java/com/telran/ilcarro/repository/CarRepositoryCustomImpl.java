package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author izum286
 */
@Repository
public class CarRepositoryCustomImpl implements CarRepositoryCustom{
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CarRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public Page<FullCarEntity> cityDatesPriceSortByPrice(String city, LocalDateTime start, LocalDateTime end,
                                                         double priceFrom, double priceTo, Pageable pageable){
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where("city").is(city));
        criteria.add(Criteria.where("pricePerDaySimple").gte(priceFrom));
        criteria.add(Criteria.where("pricePerDaySimple").lte(priceTo));
        criteria.add(Criteria.where("bookedPeriods.startDateTime").gte(end));
        criteria.add(Criteria.where("bookedPeriods.endDateTime").lte(start));
        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        List<FullCarEntity> list = mongoTemplate.find(query, FullCarEntity.class);
        return PageableExecutionUtils.getPage(list, pageable, () -> mongoTemplate.count(query, FullCarEntity.class));
    }
}
