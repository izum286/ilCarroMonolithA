package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.SearchResponse;
import com.telran.ilcarro.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;


    @ApiOperation(value = "cars by city, dates, price range,sorted by price", response = SearchResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("/search")
    public SearchResponse cityDatesPriceSortByPrice(@RequestParam(name = "city") String city,
                                                    @RequestParam (name = "start_date") String dateFrom,
                                                    @RequestParam (name = "end_date") String dateTo,
                                                    @RequestParam (name = "min_amount") String minPrice,
                                                    @RequestParam (name = "max_amount") String maxPrice,
                                                    @RequestParam (name = "ascending") String sort,
                                                    @RequestParam (name = "items_on_page") int itemsOnPage,
                                                    @RequestParam (name = "current_page")int currentPage){
        return searchService
                .cityDatesPriceSortByPrice
                        (city, dateFrom, dateTo, minPrice, maxPrice, sort, itemsOnPage, currentPage);

    }

    @ApiOperation(value = "cars by location and radius", response = SearchResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("/search/geo")
    public SearchResponse geoAndRadius(@RequestParam (name = "latitude") String latt,
                                       @RequestParam (name = "longitude") String longt,
                                       @RequestParam (name = "radius") String radius,
                                       @RequestParam (name = "items_on_page") int itemsOnPage,
                                       @RequestParam (name = "current_page") int currentPage){
        return searchService.
                geoAndRadius
                        (latt, longt, radius, itemsOnPage, currentPage);
    }

    @ApiOperation(value = "search cars by filter", response = SearchResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("/search/filters")
    public SearchResponse byFilter(@RequestBody Object filter,
                                   @RequestParam (name = "items_on_page") int itemsOnPage,
                                   @RequestParam (name = "current_page") int currentPage){
        return searchService
                .byFilter(filter, itemsOnPage, currentPage);
    }


    @ApiOperation(value = "search cars by all possible params", response = SearchResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("/search/all")
    public SearchResponse searchAllSortByPrice(@RequestParam (name = "items_on_page")int itemsOnPage,
                                               @RequestParam (name = "current_page")int currentPage,
                                               @RequestBody Object filter,
                                               @RequestParam (name = "latitude") String latt,
                                               @RequestParam (name = "longitude")String longt,
                                               @RequestParam (name = "radius")String radius,
                                               @RequestParam (name = "city")String city,
                                               @RequestParam (name = "start_date")String dateFrom,
                                               @RequestParam (name = "end_date")String dateTo,
                                               @RequestParam (name = "min_amount")String minPrice,
                                               @RequestParam (name = "max_amount")String maxPrice,
                                               @RequestParam (name = "ascending") String sort

    ){
        return searchService.searchAllSortByPrice(itemsOnPage, currentPage, filter, latt, longt, radius, city, dateFrom, dateTo, minPrice, maxPrice, sort);

    }
}
