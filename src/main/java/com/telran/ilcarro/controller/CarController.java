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

    /**
     * Returns the shortCarDTO that was created from FullCarDTO
     * @param carDTO of new car
     * @return ShortCarDTO
     */
    ShortCarDTO addCar(FullCarDTO carDTO);

    /**
     * Returns the shortCarDTO that was created from FullCarDTO
     * @param carDTO of updated car with updated params
     * @return ShortCarDTO
     */
    ShortCarDTO updateCar(FullCarDTO carDTO);

    /**
     * Method for deleting an existing car
     * @param id of car to be deleted
     */
    void deleteCar(String id);

    /**
     * Returns the FullCarDTO for regular users
     * @param id of car
     * @return FullCarDTO
     */
    FullCarDTO getCarByIdForUsers(String id);

    /**
     * Returns list of FullCarDTO for owner
     * @return List<FullCarDTO>
     */
    List<FullCarDTO> ownerGetCars();

    /**
     * Returns the FullCarDTO for car owner by id of his car
     * @param id of car
     * @return FullCarDTO
     */
    FullCarDTO ownerGetCarById(String id);

    /**
     * Returns list ShedularUsageDTO for owner about his car
     * @param id of car
     * @return List<ShedularUsageDTO>
     */
    List<SchedularUsageDTO>  ownerGetBookedPeriodsByCarId(String id);

}