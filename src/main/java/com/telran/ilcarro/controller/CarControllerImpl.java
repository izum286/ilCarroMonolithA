package com.telran.ilcarro.controller;

import com.telran.ilcarro.controller.interfaces.CarController;
import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.car.probably_unused.SchedularUsageDTO;
import com.telran.ilcarro.model.car.probably_unused.ShortCarDTO;
import com.telran.ilcarro.service.car.CarService;
import com.telran.ilcarro.service.filter.FilterService;
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
     *
     * @author Gor Aleks
     * @author izum286
     * @see CarController
     * @since 1.0
     */


    @Autowired
    CarService carService;

    @Autowired
    FilterService filterService;

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
    public FullCarDTOResponse addCar(@RequestBody AddUpdateCarDtoRequest carDTO) throws IllegalAccessException {
        filterService.addFilter(carDTO);
        return carService.addCar(carDTO).orElseThrow();
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
    public FullCarDTOResponse updateCar(@RequestBody AddUpdateCarDtoRequest carDTO) {
        return carService.updateCar(carDTO).orElseThrow();
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
    public void deleteCar(@RequestParam(name = "serial_number") String carId) {
        carService.deleteCar(carId);
    }


    //**********************************************************************************


    @ApiOperation(value = "Get car by if for users", response = FullCarDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 404, message = "Car with id: {id} not found")
    }
    )

    @Override
    @GetMapping("/car?serial_number")
    //TODO without auth
    public FullCarDTOResponse getCarByIdForUsers(@RequestParam(name = "serial_number") String carId) {
        return carService.getCarById(carId).orElseThrow();
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
    public List<FullCarDTOResponse> ownerGetCars() {
        return carService.ownerGetCars();
    }


    //**********************************************************************************


    @ApiOperation(value = "Owner get car by id", response = FullCarDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 404, message = "Car with id: {id} not found"),
            @ApiResponse(code = 409, message = "Conflict")
    }
    )

    @Override
    @GetMapping("/user/cars/car?serial_number")
    //TODO With auth
    public FullCarDTOResponse ownerGetCarById(@RequestParam(name = "serial_number") String carId) {
        return carService.getCarById(carId).orElseThrow();
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
    public List<BookedPeriodDto> ownerGetBookedPeriodsByCarId(@RequestParam(name = "serial_number") String carId) {
        return carService.ownerGetBookedPeriodsByCarId(carId);
    }

    @Override
    @PostMapping("/car/reserv")
    public BookedPeriodDto makeReservation(@RequestParam(name = "serial_number") String carId, @RequestBody BookRequestDTO dto) {
        return carService.makeReservation(carId, dto);
    }
}
