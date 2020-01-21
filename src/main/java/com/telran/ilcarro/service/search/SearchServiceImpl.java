package com.telran.ilcarro.service.search;

import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.car.SearchResponse;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.exception.RepositoryException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.filter.FilterService;
import com.telran.ilcarro.service.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author izum286
 */


@Service
public class SearchServiceImpl implements  SearchService{

    @Autowired
    CarRepository carRepository;

    @Autowired
    FilterService filterService;


    @Override
    public SearchResponse cityDatesPriceSortByPrice(String city, LocalDateTime dateFrom, LocalDateTime dateTo,
                                                    double minPrice, double maxPrice, boolean sort,
                                                    int itemsOnPage, int currentPage) {
        try {
            SearchResponse res = new SearchResponse();
            Page<FullCarEntity>
                cars = carRepository
                        .cityDatesPriceSortByPrice(city, dateFrom, dateTo, minPrice, maxPrice,
                                PageRequest.of(currentPage, itemsOnPage), sort);
            if (cars == null) throw new RepositoryException("No such Cars according to search request");
            List<FullCarDTOResponse> carDTOResponses = cars.stream().map(e -> CarMapper.INSTANCE.map(e)).collect(Collectors.toList());
            res.setCars(carDTOResponses);
            res.setCurrentPage(currentPage);
            res.setItemsOnPage(itemsOnPage);
            res.setItemsTotal(cars.getTotalElements());
            res.setMegaFilter(filterService.provideFilter());
            return res;
        } catch (Throwable t) {
            throw new RepositoryException("Something went wrong");
        }
    }

    @Override
    public SearchResponse geoAndRadius(String latitude, String longitude, String radius, int itemsOnPage, int currentPage) {
        try {
            SearchResponse res = new SearchResponse();
            Double rad = Double.valueOf(radius);
            Page<FullCarEntity> cars = new PageImpl<>(new ArrayList<>(), Pageable.unpaged(),0);
            while (cars.getTotalElements()==0 && rad<=rad+1500) {
                cars = carRepository
                        .findAllByPickUpPlaceWithin(
                                new Circle(Double.parseDouble(latitude), Double.parseDouble(longitude), rad),
                                PageRequest.of(currentPage, itemsOnPage));
                rad+=500;
            }
            if (cars.getTotalElements() == 0) throw new NotFoundServiceException("No such Cars according to search request");
            List<FullCarDTOResponse> carDTOResponses = cars.stream().map(e -> CarMapper.INSTANCE.map(e)).collect(Collectors.toList());
            res.setCars(carDTOResponses);
            res.setCurrentPage(currentPage);
            res.setItemsOnPage(itemsOnPage);
            res.setItemsTotal(cars.getTotalElements());
            res.setMegaFilter(filterService.provideFilter());
            return res;
        } catch (Throwable t) {
            throw new RepositoryException("Something went wrong");
        }
    }

    @Override
    public SearchResponse byFilter(FilterDTO filter, int itemsOnPage, int currentPage) {
        try {
            SearchResponse res = new SearchResponse();
            Page<FullCarEntity> cars  = carRepository
                        .byFilter(filter, PageRequest.of(currentPage,itemsOnPage));

            if (cars == null) throw new RepositoryException("No such Cars according to search request");
            List<FullCarDTOResponse> carDTOResponses = cars.stream().map(e -> CarMapper.INSTANCE.map(e)).collect(Collectors.toList());
            res.setCars(carDTOResponses);
            res.setCurrentPage(currentPage);
            res.setItemsOnPage(itemsOnPage);
            res.setItemsTotal(cars.getTotalElements());
            res.setMegaFilter(filterService.provideFilter());
            return res;
        } catch (Throwable t) {
            throw new RepositoryException("Something went wrong");
        }
    }

    @Override
    public SearchResponse searchAllSortByPrice(int itemsOnPage, int currentPage, FilterDTO filter,
                                               String latt, String longt, String radius, String city,
                                               LocalDateTime dateFrom, LocalDateTime dateTo,
                                               double minPrice, double maxPrice, boolean sort) {
        try {
            SearchResponse res = new SearchResponse();
            Double rad = Double.valueOf(radius);
            Page<FullCarEntity> cars = new PageImpl<>(new ArrayList<>(), Pageable.unpaged(),0);
            while (cars.getTotalElements()==0 && rad<=rad+1500) {
                cars = carRepository
                            .searchAllSortByPrice(itemsOnPage, currentPage, filter, latt, longt, rad.toString(), city, dateFrom, dateTo, minPrice, maxPrice,
                                    PageRequest.of(currentPage, itemsOnPage), sort);
                rad+=500;
            }
            if (cars.getTotalElements() == 0)
                throw new NotFoundServiceException("No such Cars according to search request");

            List<FullCarDTOResponse> carDTOResponses = cars.stream().map(e -> CarMapper.INSTANCE.map(e)).collect(Collectors.toList());
            res.setCars(carDTOResponses);
            res.setCurrentPage(currentPage);
            res.setItemsOnPage(itemsOnPage);
            res.setItemsTotal(cars.getTotalElements());
            res.setMegaFilter(filterService.provideFilter());
            return res;
        } catch (Throwable t) {
            throw new RepositoryException("Something went wrong");
        }
    }
}
