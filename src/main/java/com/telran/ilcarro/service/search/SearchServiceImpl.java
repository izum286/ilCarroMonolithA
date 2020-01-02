package com.telran.ilcarro.service.search;

import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.car.SearchResponse;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.service.filter.FilterService;
import com.telran.ilcarro.service.mapper.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Circle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author izum286
 */

//TODO - mock service. need to implement CarRepo extends MongoRepo first
@Service
public class SearchServiceImpl implements  SearchService{

    @Autowired
    CarRepository carRepository;

    @Autowired
    FilterService filterService;

    @Autowired
    MapperService mapperService;

    @Override
    public SearchResponse cityDatesPriceSortByPrice(String city, String dateFrom, String dateTo, String minPrice, String maxPrice, String sort, int itemsOnPage, int currentPage) {
        return SearchResponse.builder().megaFilter("mock").build();
    }

    @Override
    public SearchResponse geoAndRadius(String latitude, String longitude, String radius, int itemsOnPage, int currentPage) {
        SearchResponse res = new SearchResponse();
        Page<FullCarEntity> cars = carRepository
                .findAllByPickUpPlaceWithin(
                        new Circle(Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(radius)),
                        PageRequest.of(currentPage, itemsOnPage));
        List<FullCarDTOResponse> carDTOResponses = cars.stream().map(e -> mapperService.map(e)).collect(Collectors.toList());
        res.setCars(carDTOResponses);
        res.setCurrentPage(currentPage);
        res.setItemsOnPage(itemsOnPage);
        res.setItemsTotal(cars.getTotalElements());
        res.setMegaFilter(filterService.provideFilter());
        //TODO carStatistics object
        return res;
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
