package com.telran.ilcarro.controller;

import com.telran.ilcarro.controller.interfaces.SearchController;
import com.telran.ilcarro.model.car.SearchResponse;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.service.search.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author izum286
 */

//@CrossOrigin
@RestController
public class SearchControllerImpl implements SearchController {

    @Autowired
    SearchService searchService;


    /**
     *
     * @author izum286
     * status READY
     */
    @ApiOperation(value = "search cars by city, dates, price range,sorted by price", response = SearchResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("/search")
    @Override
    public SearchResponse cityDatesPriceSortByPrice(@RequestParam(name = "latitude") String latitude,
                                                    @RequestParam(name = "longitude") String longitude,
                                                    @RequestParam (name = "start_date")
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                                                LocalDateTime dateFrom,
                                                    @RequestParam (name = "end_date")
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                                                LocalDateTime dateTo,
                                                    @RequestParam (name = "min_amount") double minPrice,
                                                    @RequestParam (name = "max_amount") double maxPrice,
                                                    @RequestParam (name = "ascending") boolean sort,
                                                    @RequestParam (name = "items_on_page") int itemsOnPage,
                                                    @RequestParam (name = "current_page")int currentPage){
        double radius = 0.5;
        return searchService
                .cityDatesPriceSortByPrice
                        (latitude,  longitude,  radius, dateFrom, dateTo, minPrice, maxPrice, sort, itemsOnPage, currentPage);

    }

    /**
     *
     * @author izum286
     * status READY
     */
    @ApiOperation(value = "cars by location and radius", response = SearchResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("/search/geo")
    @Override
    public SearchResponse geoAndRadius(@RequestParam (name = "latitude") String latt,
                                       @RequestParam (name = "longitude") String longt,
                                       @RequestParam (name = "radius") String radius,
                                       @RequestParam (name = "items_on_page") int itemsOnPage,
                                       @RequestParam (name = "current_page") int currentPage){
        return searchService.
                geoAndRadius
                        (latt, longt, radius, itemsOnPage, currentPage);
    }


    /**
     *
     * @author izum286
     * status READY
     */
    @ApiOperation(value = "search cars by filter", response = SearchResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("/search/filters")
    public SearchResponse byFilter(@RequestBody FilterDTO filter,
                                   @RequestParam (name = "items_on_page") int itemsOnPage,
                                   @RequestParam (name = "current_page") int currentPage){
        return searchService
                .byFilter(filter, itemsOnPage, currentPage);
    }


    /**
     *
     * @author izum286
     * status IN_Progress
     */
    @ApiOperation(value = "search cars by all possible params", response = SearchResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("/search/all")
    @Override
    public SearchResponse searchAllSortByPrice(@RequestParam (name = "items_on_page")int itemsOnPage,
                                               @RequestParam (name = "current_page")int currentPage,
                                               @RequestBody FilterDTO filter,
                                               @RequestParam (name = "latitude") String latt,
                                               @RequestParam (name = "longitude")String longt,
                                               @RequestParam (name = "radius")String radius,
                                               @RequestParam (name = "start_date")
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                                           LocalDateTime dateFrom,
                                               @RequestParam (name = "end_date")
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                                           LocalDateTime dateTo,
                                               @RequestParam (name = "min_amount")double minPrice,
                                               @RequestParam (name = "max_amount")double maxPrice,
                                               @RequestParam (name = "ascending") boolean sort

    ){
        return searchService.searchAllSortByPrice(itemsOnPage, currentPage, filter, latt,
                longt, radius,  dateFrom, dateTo, minPrice, maxPrice, sort);

    }
}
