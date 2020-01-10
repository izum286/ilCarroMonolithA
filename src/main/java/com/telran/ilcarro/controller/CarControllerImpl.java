package com.telran.ilcarro.controller;

import com.telran.ilcarro.controller.interfaces.CarController;
import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.car.probably_unused.ShortCarDTO;
import com.telran.ilcarro.service.car.CarService;
import com.telran.ilcarro.service.filter.FilterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
/**
 * Car controller implementation
 *
 * @author Gor Aleks
 * @author izum286
 * @see CarController
 * @since 1.0
 */

@CrossOrigin
@RestController
public class CarControllerImpl implements CarController {

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

    public FullCarDTOResponse addCar(@RequestBody AddUpdateCarDtoRequest carDTO, Principal principal) throws IllegalAccessException {
        String userEmail = principal.getName();
        filterService.addFilter(carDTO);
        return carService.addCar(carDTO, userEmail).orElseThrow();
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
        public FullCarDTOResponse updateCar(@RequestBody AddUpdateCarDtoRequest carDTO, Principal principal) throws IllegalAccessException {
        filterService.addFilter(carDTO);
        String userEmail = principal.getName();
            return carService.updateCar(carDTO, userEmail).orElseThrow();
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
    public void deleteCar(@RequestParam(name = "serial_number") String carId, Principal principal) {
        String userEmail = principal.getName();
        carService.deleteCar(carId, userEmail);
    }


    //**********************************************************************************


    @ApiOperation(value = "Get car by id for users", response = FullCarDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 404, message = "Car with id: {id} not found")
    }
    )

    @Override
    @GetMapping("/car?serial_number")
    public FullCarDTOResponse getCarByIdForUsers(@RequestParam(name = "serial_number") String carId) {
        return carService.getCarByIdForUsers(carId).orElseThrow();
    }


    @ApiOperation(value = "Get car by id for users", response = FullCarDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 404, message = "Car with id: {id} not found")
    }
    )

    @GetMapping("/users/cars/car?serial_number")
    @Override
    public FullCarDTOResponse getCarByIdForOwner(@RequestParam(name = "serial_number") String carId,
                                                 Principal principal) {
        String userEmail = principal.getName();
        return carService.getCarByIdForOwner(carId, userEmail).orElseThrow();
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
    public List<FullCarDTOResponse> ownerGetCars(Principal principal) {
        String userEmail = principal.getName();
        return carService.ownerGetCars(userEmail);
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
    public FullCarDTOResponse ownerGetCarById(@RequestParam(name = "serial_number") String carId) {
        return carService.getCarByIdForUsers(carId).orElseThrow();
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
    public List<BookedPeriodDto> ownerGetBookedPeriodsByCarId(@RequestParam(name = "serial_number") String carId,
                                                              Principal principal) {
        String userEmail = principal.getName();
        return carService.getBookedPeriodsByCarId(carId, userEmail);
    }

    @Override
    @PostMapping("/car/reservation?serial_number")
    public BookResponseDTO makeReservation(@RequestParam(name = "serial_number") String carId,
                                           @RequestBody BookRequestDTO dto, Principal principal) {
        String userEmail = principal.getName();
        return carService.makeReservation(carId, dto, userEmail).orElseThrow();
    }

    @GetMapping("/car/best")
    public List<FullCarDTOResponse> getThreeBestCars() {
        return carService.getThreeBestCars();
    }

}
