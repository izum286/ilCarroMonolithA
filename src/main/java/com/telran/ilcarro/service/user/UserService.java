package com.telran.ilcarro.service.user;

import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;

import java.util.List;
import java.util.Optional;
/**
 *
 * UserService interface
 * @author Konkin Anton
 * 19.12.2019
 */
public interface UserService {
    Optional<FullUserDTO> addUser(String userEmail, RegUserDTO regUser) throws ConflictServiceException;
    Optional<FullUserDTO> getUser(String email) throws NotFoundServiceException;
    Optional<FullUserDTO> updateUser(String email, UpdUserDTO updUser) throws NotFoundServiceException, ConflictServiceException;
    boolean deleteUser(String email) throws NotFoundServiceException;

    /**
     * Method add carId to user cars
     * @param userID userId to update information
     * @param carID car serial number to add
     * @return true
     * @throws NotFoundServiceException - if user not found
     * @throws ConflictServiceException - if carId already in list
     */
    boolean addUserCar(String userID, String carID) throws NotFoundServiceException, ConflictServiceException;
    boolean ifUserCarsExist(String userID, String carID) throws NotFoundServiceException;
    Optional<List<BookedPeriodDto>> getUserBookedCarsPeriods(String userID) throws NotFoundServiceException;

    /**
     * Method add bookedPeriods to user history
     * @param userID user ID
     * @param bookedPeriodEntity entity to add
     * @return true when booked period successfully added
     * @throws NotFoundServiceException - in case when userID not found
     */
    boolean addBookedPeriodToUserHistory(String userID, BookedPeriodEntity bookedPeriodEntity) throws NotFoundServiceException;
}
