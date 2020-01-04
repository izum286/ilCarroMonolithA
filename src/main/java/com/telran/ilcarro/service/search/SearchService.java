package com.telran.ilcarro.service.search;

import com.telran.ilcarro.model.car.SearchResponse;
import com.telran.ilcarro.model.filter.FilterDTO;

import java.time.LocalDateTime;

public interface SearchService {
     SearchResponse cityDatesPriceSortByPrice(String city,
                                                    LocalDateTime dateFrom,
                                                    LocalDateTime dateTo,
                                                    double minPrice,
                                                    double maxPrice,
                                                    String sort,
                                                    int itemsOnPage,
                                                    int currentPage);

     SearchResponse geoAndRadius (String latt,
                                        String longt,
                                        String radius,
                                        int itemsOnPage,
                                        int currentPage);

     SearchResponse byFilter (FilterDTO filter,
                              int itemsOnPage,
                              int currentPage);

     SearchResponse searchAllSortByPrice (int itemsOnPage, int currentPage,Object filter, String latt, String longt,  String radius,
                                                String city, String dateFrom,
                                                String dateTo, String minPrice, String maxPrice, String sort
    );
}
