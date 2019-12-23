package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.model.web.SchedularUsageDTO;
import com.telran.ilcarro.model.web.ShortCarDTO;
import com.telran.ilcarro.service.CarService;
import com.telran.ilcarro.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CarControllerImpl implements CarController {

    @Autowired
    CarService carService;

    @Override
    @PostMapping("/car")
    public ShortCarDTO addCar(@RequestBody FullCarDTO carDTO) {
        return carService.addCar(carDTO);
    }

    @Override
    @PutMapping("/car")
    public ShortCarDTO updateCar(@RequestBody FullCarDTO carDTO) {
        return carService.updateCar(carDTO);
    }

    @Override
    @DeleteMapping("/car?serial_number")
    public void deleteCar(@RequestParam(name = "serial_number") String id) {
        carService.deleteCar(id);
    }

    @Override
    @GetMapping("/car?serial_number")
    public FullCarDTO getCarByIdForUsers(@RequestParam(name = "serial_number") String id) {
        return carService.getCarByIdForUsers(id);
    }

    @Override
    @GetMapping("/user/cars")
    public List<FullCarDTO> ownerGetCars() {
        return carService.ownerGetCars();
    }

    @Override
    @GetMapping("/user/cars/car?serial_number")
    public FullCarDTO ownerGetCarById(@RequestParam(name = "serial_number") String id) {
        return carService.ownerGetCarById(id);
    }

    @Override
    @GetMapping("/user/cars/periods?serial_number")
    public List<SchedularUsageDTO> ownerGetBookedPeriodsByCarId(@RequestParam(name = "serial_number") String id) {
        return carService.ownerGetBookedPeriodsByCarId(id);
    }
}
