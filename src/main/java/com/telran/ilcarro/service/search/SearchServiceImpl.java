package com.telran.ilcarro.service.search;

import com.telran.ilcarro.model.car.SearchResponse;
import com.telran.ilcarro.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO - mock service. need to implement CarRepo extends MongoRepo first
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    CarRepo carRepo;

    @Override
    public SearchResponse cityDatesPriceSortByPrice(String city, String dateFrom, String dateTo, String minPrice, String maxPrice, String sort, int itemsOnPage, int currentPage) {
        return SearchResponse.builder().megaFilter("mock").build();
    }

    @Override
    public SearchResponse geoAndRadius(String latt, String longt, String radius, int itemsOnPage, int currentPage) {
        return SearchResponse.builder().megaFilter("mock").build();
    }

    @Override
    public SearchResponse byFilter(Object filter, int itemsOnPage, int currentPage) {
        return SearchResponse.builder().megaFilter("mock").build();
    }

    @Override
    public SearchResponse searchAllSortByPrice(int itemsOnPage, int currentPage, Object filter, String latt, String longt, String radius, String city, String dateFrom, String dateTo, String minPrice, String maxPrice, String sort) {
        return SearchResponse.builder().megaFilter("mock").build();
    }
}
