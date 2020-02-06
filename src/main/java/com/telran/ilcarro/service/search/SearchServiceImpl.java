package com.telran.ilcarro.service.search;

import com.telran.ilcarro.annotaion.CheckForNull;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.car.SearchResponse;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.exception.RepositoryException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
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
    @CheckForNull
    public SearchResponse cityDatesPriceSortByPrice(String latitude, String longitude, double radius, LocalDateTime dateFrom, LocalDateTime dateTo,
                                                    double minPrice, double maxPrice, boolean sort,
                                                    int itemsOnPage, int currentPage) {
        currentPage = (currentPage <= 0) ? 1: currentPage;
        Double rad = radius;
        double maxRad = rad+1500;
        Page<FullCarEntity> cars = new PageImpl<>(new ArrayList<>(), Pageable.unpaged(),0);
        while ((cars.getTotalElements() == 0) & (rad<=maxRad)) {
            try {
                cars = carRepository
                        .cityDatesPriceSortByPrice(latitude, longitude, rad, dateFrom, dateTo, minPrice, maxPrice,
                                PageRequest.of(currentPage, itemsOnPage), sort);
            } catch (Exception e) {
                System.out.println("err"+e.getMessage());
            }
            rad+=500;
        }
        if (cars.getTotalElements() == 0) throw new NotFoundServiceException("No such Cars according to search request");
            SearchResponse res = new SearchResponse();
            List<FullCarDTOResponse> carDTOResponses = cars.getContent().stream().map(e -> CarMapper.INSTANCE.map(e)).collect(Collectors.toList());
            res.setCars(carDTOResponses);
            res.setCurrentPage(cars.getPageable().getPageNumber());
            res.setItemsOnPage(cars.getPageable().getPageSize());
            res.setItemsTotal(cars.getTotalElements());
            res.setMegaFilter(filterService.provideFilter());
            return res;
    }

    @Override
    @CheckForNull
    public SearchResponse geoAndRadius(String latitude, String longitude, String radius, int itemsOnPage, int currentPage) {
        currentPage = (currentPage <= 0) ? 1: currentPage;
        Double rad = Double.valueOf(radius);
            double maxRad = rad+1500;
            Page<FullCarEntity> cars = new PageImpl<>(new ArrayList<>(), Pageable.unpaged(),0);
            while ((cars.getTotalElements() == 0) & (rad<=maxRad)) {
                try {
                    cars = carRepository
                            .findAllByPickUpPlaceWithin(
                                    new Circle(Double.parseDouble(latitude), Double.parseDouble(longitude), rad),
                                    PageRequest.of(currentPage, itemsOnPage));
                } catch (Throwable e) {
                    throw new ServiceException("Something went wrong");
                }
                rad+=500;
            }
            if (cars.getTotalElements() == 0) throw new NotFoundServiceException("No such Cars according to search request");
            SearchResponse res = new SearchResponse();
            List<FullCarDTOResponse> carDTOResponses = cars
                    .getContent().stream()
                    .map(e -> CarMapper.INSTANCE.map(e))
                    .collect(Collectors.toList());
            res.setCars(carDTOResponses);
            res.setCurrentPage(cars.getPageable().getPageNumber());
            res.setItemsOnPage(cars.getPageable().getPageSize());
            res.setItemsTotal(cars.getTotalElements());
            res.setMegaFilter(filterService.provideFilter());
            return res;
    }

    @Override
    @CheckForNull
    public SearchResponse byFilter(FilterDTO filter, int itemsOnPage, int currentPage) {
        currentPage = (currentPage <= 0) ? 1: currentPage;
        Page<FullCarEntity> cars;
        try {
             cars  = carRepository
                        .byFilter(filter, PageRequest.of(currentPage,itemsOnPage));

            } catch (Throwable t) {
                throw new NotFoundServiceException("Something went wrong");
            }
            if (cars.getTotalElements() == 0) throw new NotFoundServiceException("No such Cars according to search request");
            SearchResponse res = new SearchResponse();
            List<FullCarDTOResponse> carDTOResponses = cars.stream().map(e -> CarMapper.INSTANCE.map(e)).collect(Collectors.toList());
            res.setCars(carDTOResponses);
            res.setCurrentPage(cars.getPageable().getPageNumber());
            res.setItemsOnPage(cars.getPageable().getPageSize());
            res.setItemsTotal(cars.getTotalElements());
            res.setMegaFilter(filterService.provideFilter());
            return res;

    }

    @Override
    @CheckForNull
    public SearchResponse searchAllSortByPrice(int itemsOnPage, int currentPage, FilterDTO filter,
                                               String latt, String longt, String radius,
                                               LocalDateTime dateFrom, LocalDateTime dateTo,
                                               double minPrice, double maxPrice, boolean sort) {
        currentPage = (currentPage <= 0) ? 1: currentPage;
        Double rad = Double.valueOf(radius);
            double maxRad = rad+1500;
            Page<FullCarEntity> cars = new PageImpl<>(new ArrayList<>(), Pageable.unpaged(),0);
            while ((cars.getTotalElements()==0) & (rad<=maxRad)) {
                try {
                    cars = carRepository
                                .searchAllSortByPrice(itemsOnPage, currentPage, filter, latt, longt, rad.toString(),  dateFrom, dateTo, minPrice, maxPrice,
                                        PageRequest.of(currentPage, itemsOnPage), sort);
                } catch (Throwable e) {
                    throw new ServiceException("Something went wrong");
                }
                rad+=500;
            }
            if (cars.getTotalElements() == 0)
                throw new NotFoundServiceException("No such Cars according to search request");
            SearchResponse res = new SearchResponse();
            List<FullCarDTOResponse> carDTOResponses = cars
                    .getContent().stream()
                    .map(e -> CarMapper.INSTANCE.map(e)).collect(Collectors.toList());
            res.setCars(carDTOResponses);
            res.setCurrentPage(cars.getPageable().getPageNumber());
            res.setItemsOnPage(cars.getPageable().getPageSize());
            res.setItemsTotal(cars.getTotalElements());
            res.setMegaFilter(filterService.provideFilter());
            return res;
    }
}
