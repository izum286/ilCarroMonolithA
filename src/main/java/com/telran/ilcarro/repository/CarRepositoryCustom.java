package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
/**
 * @author izum286
 */
public interface CarRepositoryCustom {
    Page<FullCarEntity> cityDatesPriceSortByPrice (String city, LocalDateTime start, LocalDateTime end,
                                                   double priceFrom, double priceTo, Pageable pageable);
}
