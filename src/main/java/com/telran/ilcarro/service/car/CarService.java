package com.telran.ilcarro.service.car;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.EmptyDataException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * CarService interface
 * @author Aleks Gor
 * 03.01.2020
 */

public interface CarService {

    /**
     * Add new Car as AddUpdateCarDtoRequest
     * @param carDTO Data transfer object
     * @return Optional of FullCarDTOResponse
     * @throws EmptyDataException - if data is empty
     * @throws ConflictServiceException - if car with current serial number already exists
     * @throws ServiceException - if user unauthorized
     */
    Optional<FullCarDTOResponse> addCar(AddUpdateCarDtoRequest carDTO, String userEmail);

    /**
     * Update exists Car as AddUpdateCarDtoRequest
     * @param carDTO Data transfer object
     * @return Optional of FullCarDTOResponse
     * @throws EmptyDataException - if data is empty
     * @throws NotFoundServiceException - if car with current serial number was not found
     * @throws ServiceException - if user unauthorized
     */
    Optional<FullCarDTOResponse> updateCar(AddUpdateCarDtoRequest carDTO, String userEmail);

    /**
     * Delete exists Car by serial number as carId
     * @param carId String
     * @return Boolean true if car was deleted and false if car was not deleted
     * @throws NotFoundServiceException - if car with current serial number was not found
     * @throws ServiceException - if user unauthorized
     */
    boolean deleteCar(String carId, String userEmail);

    /**
     * Get list of FullCarDtoResponse for cars owner
     * @return List of FullCarDTOResponse
     * @throws NotFoundServiceException - if car with current serial number was not found
     * @throws ServiceException - if user unauthorized
     */
    List<FullCarDTOResponse> ownerGetCars();

    /**
     * Get FullCarDTOResponse by car serial number as carId
     * @param carId String
     * @return Optional of FullCarDTOResponse
     * @throws NotFoundServiceException - if car with current serial number was not found
     * @throws ServiceException - if user unauthorized
     */
    Optional<FullCarDTOResponse> getCarByIdForUsers(String carId);

    /**
     * Get List of BookedPeriods for current car by his serial number as carId
     * @param carId String
     * @return List of BookedPeriodDTO
     * @throws NotFoundServiceException - if car with current serial number was not found
     * @throws ServiceException - if user unauthorized
     * @throws ConflictServiceException - //TODO что в данном методе, согласно протоколу, является конфликтом?
     */
    List<BookedPeriodDto> getBookedPeriodsByCarId(String carId);

    /**
     * Make new BookedPeriodDTO as dto for current car with serial number as carId
     * @param carId String
     * @param dto BookRequestDTO
     * @return Optional of FullCarDTOResponse
     * @throws NotFoundServiceException - if car with current serial number was not found
     * @throws ConflictServiceException - if current car already booked with current date
     * @throws ServiceException - if user unauthorized
     */
    Optional<BookResponseDTO> makeReservation(String carId, BookRequestDTO dto, String userEmail);

    /**
     * Get OwnerDtoResponse of current car with serial number as carId
     * @param carId String
     * @return Optional of OwnerDtoResponse
     * @throws NotFoundServiceException - if car with current serial number was not found
     * @throws ServiceException - if user unauthorized
     */
    Optional<OwnerDtoResponse> getOwnerByCarId(String carId);

    /**
     * Get Car statistic of trips and rating of current car with serial number as carId
     * @param carId String
     * @return List of CarStatDTO
     * @throws NotFoundServiceException - if car with current serial number was not found
     * @throws ServiceException - if user unauthorized
     */
    List<CarStatDto> getCarStatById(String carId);


    List<FullCarDTOResponse> getThreeBestCars();
}