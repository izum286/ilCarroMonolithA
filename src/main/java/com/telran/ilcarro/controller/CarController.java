package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.model.web.SchedularUsageDTO;
import com.telran.ilcarro.model.web.ShortCarDTO;

import java.util.List;

/**
 * Car controller interface
 * Main methods to get, update and delete information about cars.
 *
 *
 * @author Gor Aleks
 * @since 1.0
 *
 */

public interface CarController {

    ShortCarDTO addCar(FullCarDTO carDTO);

    ShortCarDTO updateCar(FullCarDTO carDTO);

    void deleteCar(String id);

    FullCarDTO getCarByIdForUsers(String id);

    List<FullCarDTO> ownerGetCars();

    FullCarDTO ownerGetCarById(String id);

    List<SchedularUsageDTO>  ownerGetBookedPeriodsByCarId(String id);

}