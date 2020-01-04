package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
/**
 * @author izum286
 */
public interface CarRepositoryCustom {
    Page<FullCarEntity> cityDatesPriceSortByPrice (String city, LocalDateTime start, LocalDateTime end,
                                                   double priceFrom, double priceTo, Pageable pageable, boolean sort);

    Page<FullCarEntity> byFilter(FilterDTO filter, Pageable pageable);
    Page<FullCarEntity> searchAllSortByPrice(int itemsOnPage, int currentPage, FilterDTO filter,
                                             String latt, String longt, String radius, String city,
                                             LocalDateTime dateFrom, LocalDateTime dateTo,
                                             double minPrice, double maxPrice,  Pageable pageable, boolean sort);

}
