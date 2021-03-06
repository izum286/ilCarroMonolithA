package com.telran.ilcarro.controller;

import com.telran.ilcarro.controller.interfaces.FilterController;
import com.telran.ilcarro.service.filter.FilterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author izum286
 */
//@CrossOrigin
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

    @RequestMapping(value = "/filters", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public String getFilters(){
        return filterService.provideFilter();
    }

    /**
     * deleting all filters from db
     * use CAREFULLY!!!!!!
     * @return String
     */
    @DeleteMapping("filters")
    public String deleteAll() {
        filterService.deleteFilters();
        return filterService.provideFilter();
    }
}
