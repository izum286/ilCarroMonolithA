package com.telran.ilcarro.controller.interfaces;

import com.telran.ilcarro.model.car.SearchResponse;
import com.telran.ilcarro.model.filter.FilterDTO;

import java.time.LocalDateTime;

/**
 * @author izum286
 */

public interface SearchController {


     SearchResponse cityDatesPriceSortByPrice(String city, LocalDateTime dateFrom, LocalDateTime dateTo, double minPrice, double maxPrice,
                                              boolean sort, int itemsOnPage, int currentPage);


     SearchResponse geoAndRadius(String latt, String longt, String radius,
                                       int itemsOnPage, int currentPage);


     SearchResponse byFilter(FilterDTO filter, int itemsOnPage, int currentPage);


     SearchResponse searchAllSortByPrice(int itemsOnPage, int currentPage, FilterDTO filter, String latt,
                                               String longt, String radius, String city, LocalDateTime dateFrom,
                                               LocalDateTime dateTo, double minPrice, double maxPrice,
                                               boolean sort);
}
