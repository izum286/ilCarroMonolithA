package com.telran.ilcarro.controller.interfaces;

import com.telran.ilcarro.model.car.*;

import java.util.List;

/**
 * Car controller interface
 * Main methods to get, update and delete information about cars.
 *
 * @author Gor Aleks
 * @since 1.0
 */

public interface CarController {

    /**
     * Returns the shortCarDTO that was created from FullCarDTO
     *
     * @param carDTO of new car
     * @return ShortCarDTO
     */
    FullCarDTOResponse addCar(AddUpdateCarDtoRequest carDTO) throws IllegalAccessException;

    /**
     * Returns the shortCarDTO that was created from FullCarDTO
     *
     * @param carDTO of updated car with updated params
     * @return ShortCarDTO
     */
    FullCarDTOResponse updateCar(AddUpdateCarDtoRequest carDTO) throws IllegalAccessException;


    /**
     * Method for deleting an existing car
     *
     * @param carId of car to be deleted
     */
    void deleteCar(String carId);

    /**
     * Returns the FullCarDTO for regular users
     *
     * @param carId of car
     * @return FullCarDTO
     */
    FullCarDTOResponse getCarByIdForUsers(String carId);

    /**
     * Returns list of FullCarDTO for owner
     *
     * @return List<FullCarDTO>
     */
    List<FullCarDTOResponse> ownerGetCars();

    /**
     * Returns the FullCarDTO for car owner by id of his car
     *
     * @param carId of car
     * @return FullCarDTO
     */
    FullCarDTOResponse ownerGetCarById(String carId);

    /**
     * Returns list ShedularUsageDTO for owner about his car
     *
     * @param carId of car
     * @return List<ShedularUsageDTO>
     */
    List<BookedPeriodDto> ownerGetBookedPeriodsByCarId(String carId);

    BookResponseDTO makeReservation(String carId, BookRequestDTO dto);

    /**
     *
     * @return List<FullCarDTO>
     */
    List<FullCarDTOResponse> getThreeBestCars();


}