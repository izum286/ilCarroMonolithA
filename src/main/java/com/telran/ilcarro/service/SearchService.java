package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.SearchResponse;

public interface SearchService {
    public SearchResponse cityDatesPriceSortByPrice(String city,
                                                    String dateFrom,
                                                    String dateTo,
                                                    String minPrice,
                                                    String maxPrice,
                                                    String sort,
                                                    int itemsOnPage,
                                                    int currentPage);

    public SearchResponse geoAndRadius (String latt,
                                        String longt,
                                        String radius,
                                        int itemsOnPage,
                                        int currentPage);

    public SearchResponse byFilter (Object filter,
                                    int itemsOnPage,
                                    int currentPage);

    public SearchResponse searchAllSortByPrice (int itemsOnPage, int currentPage,Object filter, String latt, String longt,  String radius,
                                                String city, String dateFrom,
                                                String dateTo, String minPrice, String maxPrice, String sort
    );
}
