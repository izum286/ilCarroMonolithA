package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
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
                                                         double priceFrom, double priceTo, Pageable pageable, boolean sort){
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();
        if(sort){
            query.with(Sort.by(Sort.Direction.ASC, "pricePerDaySimple"));
        }
        criteria.add(Criteria.where("city").is(city));
        criteria.add(Criteria.where("pricePerDaySimple").gte(priceFrom));
        criteria.add(Criteria.where("pricePerDaySimple").lte(priceTo));
        criteria.add(Criteria.where("bookedPeriods.startDateTime").gte(end));
        criteria.add(Criteria.where("bookedPeriods.endDateTime").lte(start));
        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        List<FullCarEntity> list = mongoTemplate.find(query, FullCarEntity.class);
        return PageableExecutionUtils.getPage(list, pageable, () -> mongoTemplate.count(query, FullCarEntity.class));
    }

    @Override
    public Page<FullCarEntity> byFilter(FilterDTO filter, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();
        if(filter.getMake()!=null){
            criteria.add(Criteria.where("make").is(filter.getMake()));
        }
        if(filter.getModels()!=null){
            criteria.add(Criteria.where("model").is(filter.getModels()));
        }
        if(filter.getYears()!=null){
            criteria.add(Criteria.where("year").is(filter.getYears()));
        }
        if(filter.getEngines()!=null){
            criteria.add(Criteria.where("engine").is(filter.getEngines()));
        }
        if(filter.getFuel()!=null){
            criteria.add(Criteria.where("fuel").is(filter.getFuel()));
        }
        if(filter.getTransmissions()!=null){
            criteria.add(Criteria.where("gear").is(filter.getTransmissions()));
        }
        if(filter.getWd()!=null){
            criteria.add(Criteria.where("wheelsDrive").is(filter.getWd()));
        }
        if(filter.getHorsepower()!=null){
            criteria.add(Criteria.where("horsePower").is(filter.getHorsepower()));
        }
        if(filter.getTorque()!=null){
            criteria.add(Criteria.where("torque").is(filter.getTorque()));
        }
        if(filter.getDoors()!=null){
            criteria.add(Criteria.where("doors").is(filter.getDoors()));
        }
        if(filter.getSeats()!=null){
            criteria.add(Criteria.where("seats").is(filter.getSeats()));
        }
        if(filter.getClasss()!=null){
            criteria.add(Criteria.where("carClass").is(filter.getClasss()));
        }
        if(filter.getFuelConsumption()!=null){
            criteria.add(Criteria.where("fuelConsumption").is(filter.getFuelConsumption()));
        }
        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        List<FullCarEntity> list = mongoTemplate.find(query, FullCarEntity.class);
        return PageableExecutionUtils.getPage(list, pageable, () -> mongoTemplate.count(query, FullCarEntity.class));
    }

    @Override
    public Page<FullCarEntity> searchAllSortByPrice(int itemsOnPage, int currentPage, FilterDTO filter,
                                                    String latt, String longt, String radius, String city,
                                                    LocalDateTime dateFrom, LocalDateTime dateTo,
                                                    double minPrice, double maxPrice, Pageable pageable, boolean sort) {

        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();
        if(sort){
            query.with(Sort.by(Sort.Direction.ASC, "pricePerDaySimple"));
        }


        if(latt!=null && longt!=null && radius!=null){
            Point point = new Point(Double.parseDouble(latt), Double.parseDouble(longt));
            Distance distance = new Distance(Double.parseDouble(radius), Metrics.KILOMETERS);
            Circle circle = new Circle(point, distance);
            Criteria geoCriteria = Criteria.where("pickUpPlace").withinSphere(circle);
            query.addCriteria(geoCriteria);
        }
        if(city!=null){
            criteria.add(Criteria.where("city").is(city));
        }
        criteria.add(Criteria.where("pricePerDaySimple").gte(minPrice));
        criteria.add(Criteria.where("pricePerDaySimple").lte(maxPrice));
        criteria.add(Criteria.where("bookedPeriods.startDateTime").gte(dateTo));
        criteria.add(Criteria.where("bookedPeriods.endDateTime").lte(dateFrom));

        if(filter.getMake()!=null){
            criteria.add(Criteria.where("make").is(filter.getMake()));
        }
        if(filter.getModels()!=null){
            criteria.add(Criteria.where("model").is(filter.getModels()));
        }
        if(filter.getYears()!=null){
            criteria.add(Criteria.where("year").is(filter.getYears()));
        }
        if(filter.getEngines()!=null){
            criteria.add(Criteria.where("engine").is(filter.getEngines()));
        }
        if(filter.getFuel()!=null){
            criteria.add(Criteria.where("fuel").is(filter.getFuel()));
        }
        if(filter.getTransmissions()!=null){
            criteria.add(Criteria.where("gear").is(filter.getTransmissions()));
        }
        if(filter.getWd()!=null){
            criteria.add(Criteria.where("wheelsDrive").is(filter.getWd()));
        }
        if(filter.getHorsepower()!=null){
            criteria.add(Criteria.where("horsePower").is(filter.getHorsepower()));
        }
        if(filter.getTorque()!=null){
            criteria.add(Criteria.where("torque").is(filter.getTorque()));
        }
        if(filter.getDoors()!=null){
            criteria.add(Criteria.where("doors").is(filter.getDoors()));
        }
        if(filter.getSeats()!=null){
            criteria.add(Criteria.where("seats").is(filter.getSeats()));
        }
        if(filter.getClasss()!=null){
            criteria.add(Criteria.where("carClass").is(filter.getClasss()));
        }
        if(filter.getFuelConsumption()!=null){
            criteria.add(Criteria.where("fuelConsumption").is(filter.getFuelConsumption()));
        }

        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        List<FullCarEntity> list = mongoTemplate.find(query, FullCarEntity.class);
        return PageableExecutionUtils.getPage(list, pageable, () -> mongoTemplate.count(query, FullCarEntity.class));
    }
}
