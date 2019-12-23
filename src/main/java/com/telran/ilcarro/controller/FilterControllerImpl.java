package com.telran.ilcarro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telran.ilcarro.service.FilterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterControllerImpl implements FilterController {

    @Autowired
    FilterService filterService;

    @ApiOperation(value = "GetFilters", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "There is no filters yet"),
    }
    )
    @GetMapping("filters")
    @Override
    public String getFilters() throws JsonProcessingException {
        return filterService.provideFilter();
    }
}
