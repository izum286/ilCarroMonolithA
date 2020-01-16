package com.telran.ilcarro.controller.interfaces;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.user.AuthDTO;
import com.telran.ilcarro.model.user.RegUserDTO;

import java.security.Principal;
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
    FullCarDTOResponse addCar(AddUpdateCarDtoRequest carDTO, Principal principal);

    /**
     * Returns the shortCarDTO that was created from FullCarDTO
     *
     * @param carDTO of updated car with updated params
     * @return ShortCarDTO
     */
    FullCarDTOResponse updateCar(String serial_number, AddUpdateCarDtoRequest carDTO , Principal principal);


    /**
     * Method for deleting an existing car
     *
     * @param carId of car to be deleted
     */
    void deleteCar(String carId, Principal principal);

    /**
     * Returns the FullCarDTO for regular users
     *
     * @param carId of car
     * @return FullCarDTO
     */
    FullCarDTOResponse getCarByIdForUsers(String carId);

    /**
     * Returns the FullCarDTO for authentificated user
     *
     * @param carId of car
     * @return FullCarDTO
     */
    FullCarDTOResponse getCarByIdForOwner(String carId, Principal principal);



    /**
     * Returns list of FullCarDTO for owner
     *
     * @return List<FullCarDTO>
     */
    List<FullCarDTOResponse> ownerGetCars(Principal principal);

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
    List<BookedPeriodDto> ownerGetBookedPeriodsByCarId(String carId, Principal principal);

    BookResponseDTO makeReservation(String carId, BookRequestDTO dto, Principal principal);

    /**
     *
     * @return List<FullCarDTO>
     */
    List<FullCarDTOResponse> getThreeBestCars();


}