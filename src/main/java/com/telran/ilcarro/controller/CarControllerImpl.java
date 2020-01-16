package com.telran.ilcarro.controller;

import com.telran.ilcarro.controller.interfaces.CarController;
import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.car.probably_unused.ShortCarDTO;
import com.telran.ilcarro.service.car.CarService;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.FilterServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.filter.FilterService;
import com.telran.ilcarro.service.user.UserService;
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

@RestController
public class CarControllerImpl implements CarController {

    @Autowired
    CarService carService;

    @Autowired
    FilterService filterService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "Add new car", response = FullCarDTOResponse .class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 409, message = "Car already exists")
    }
    )


    @PostMapping("car")
    @Override
    public FullCarDTOResponse addCar(@RequestBody AddUpdateCarDtoRequest carDTO, Principal principal) {
        String userEmail = principal.getName();
        try {
            filterService.addFilter(carDTO);
        } catch (IllegalAccessException ex) {
            throw new FilterServiceException("Something go wrong", ex.getCause());
        }
        return carService.addCar(carDTO, userEmail).orElseThrow(() -> new ConflictServiceException(String.format("Car %s already exist", carDTO.getSerialNumber())));
    }

    //**********************************************************************************


    @ApiOperation(value = "Update car", response = FullCarDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized. Please login"),
            @ApiResponse(code = 409, message = "Conflict")
    }
    )

    @Override
    @PutMapping("/car")
        public FullCarDTOResponse updateCar(@RequestParam("serial_number") String serial_number, @RequestBody AddUpdateCarDtoRequest carDTO, Principal principal) {
        //TODO check serial inside dto and serial_number or ->
        carDTO.setSerialNumber(serial_number);
        try {
            filterService.addFilter(carDTO);
        } catch (IllegalAccessException ex) {
            throw new FilterServiceException("Something go wrong", ex.getCause());
        }
        String userEmail = principal.getName();
        //TODO add correct exception
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
    @DeleteMapping("/car")
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
    @GetMapping("/car")
    public FullCarDTOResponse getCarByIdForUsers(@RequestParam(name = "serial_number") String carId) {
        //TODO Exception
        //Check bookedPeriod to NULL
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
