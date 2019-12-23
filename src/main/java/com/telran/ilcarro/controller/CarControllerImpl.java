package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.model.web.SchedularUsageDTO;
import com.telran.ilcarro.model.web.ShortCarDTO;
import com.telran.ilcarro.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CarControllerImpl implements CarController {

    /**
     * Car controller implementation
     * @see CarController
     *
     * @author Gor Aleks
     * @since 1.0
     */



    @Autowired
    CarService carService;

    @ApiOperation(value = "Add new car", response = ShortCarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 409, message = "Car already exists")
    }
    )

    @Override
    @PostMapping("/car")
    public ShortCarDTO addCar(@RequestBody FullCarDTO carDTO) {
        return carService.addCar(carDTO);
    }


    //**********************************************************************************


    @ApiOperation(value = "Update car", response = ShortCarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 409, message = "Conflict")
    }
    )

    @Override
    @PutMapping("/car")
    public ShortCarDTO updateCar(@RequestBody FullCarDTO carDTO) {
        return carService.updateCar(carDTO);
    }


    //**********************************************************************************


    @ApiOperation(value = "Delete car")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 404, message = "Car with id: {id} not found"),
            @ApiResponse(code = 409, message = "Conflict")
    }
    )

    @Override
    @DeleteMapping("/car?serial_number")
    public void deleteCar(@RequestParam(name = "serial_number") String id) {
        carService.deleteCar(id);
    }


    //**********************************************************************************


    @ApiOperation(value = "Get car by if for users", response = FullCarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 404, message = "Car with id: {id} not found")
    }
    )

    @Override
    @GetMapping("/car?serial_number")
    public FullCarDTO getCarByIdForUsers(@RequestParam(name = "serial_number") String id) {
        return carService.getCarByIdForUsers(id);
    }


    //**********************************************************************************


    @ApiOperation(value = "Owner get cars", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 404, message = "Car not found"),
            @ApiResponse(code = 409, message = "Conflict")
    }
    )

    @Override
    @GetMapping("/user/cars")
    public List<FullCarDTO> ownerGetCars() {
        return carService.ownerGetCars();
    }


    //**********************************************************************************


    @ApiOperation(value = "Owner get car by id", response = FullCarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 404, message = "Car with id: {id} not found"),
            @ApiResponse(code = 409, message = "Conflict")
    }
    )

    @Override
    @GetMapping("/user/cars/car?serial_number")
    public FullCarDTO ownerGetCarById(@RequestParam(name = "serial_number") String id) {
        return carService.ownerGetCarById(id);
    }


    //**********************************************************************************


    @ApiOperation(value = "Owner get booked period by car id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 404, message = "Car with id: {id} not found"),
            @ApiResponse(code = 409, message = "Conflict")
    }
    )

    @Override
    @GetMapping("/user/cars/periods?serial_number")
    public List<SchedularUsageDTO> ownerGetBookedPeriodsByCarId(@RequestParam(name = "serial_number") String id) {
        return carService.ownerGetBookedPeriodsByCarId(id);
    }
}
