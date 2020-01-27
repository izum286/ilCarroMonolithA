package com.telran.ilcarro.service.user;


import com.telran.ilcarro.annotaion.CheckForNull;
import com.telran.ilcarro.model.car.BookedCarDto;
import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.service.car.CarService;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.mapper.BookedPeriodMapper;
import com.telran.ilcarro.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserServiceImpl implementation of UserService
 *
 * @author Konkin Anton
 * 19.12.2019
 * @see UserService
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserEntityRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    CarService carService;

    @Override
    @CheckForNull
    public Optional<FullUserDTO> addUser(String email, RegUserDTO regUser) {
        UserEntity entity = userRepository.save(UserMapper.INSTANCE.map(email, regUser));
        return getUser(entity.getEmail());
    }

    @Override
    @CheckForNull
    public Optional<FullUserDTO> getUser(String email) {
        UserEntity entity = userRepository.findById(email)
                .orElseThrow(() -> new NotFoundServiceException(String.format("User %s not found", email)));
        if (entity.getHistory() == null) {
            entity.setHistory(new ArrayList<>());
        }
        if (entity.getComments() == null) {
            entity.setComments(new ArrayList<>());
        }
        FullUserDTO userDTO = UserMapper.INSTANCE.map(entity);
        userDTO.setBooked_car(getUserBookedCarsPeriods(email).orElse(new ArrayList<>()));
        userDTO.setOwn_cars(carService.ownerGetCars(email));
        userDTO.setHistory(entity.getHistory().stream()
                .map(BookedPeriodMapper.INSTANCE::mapBookedPeriodToBookedCarDto)
                .collect(Collectors.toList()));
        return Optional.of(userDTO);
    }

    @Override
    @CheckForNull
    public Optional<FullUserDTO> updateUser(String email, UpdUserDTO updUser) {
        if (!userDetailsRepository.existsById(email)) {
            throw new NotFoundServiceException(String.format("User %s not found", email));
        }
        UserEntity userToUpd = userRepository.findById(email)
                .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", email)));
        UserMapper.INSTANCE.updUserInfo(userToUpd, updUser);
        userRepository.save(userToUpd);
        return getUser(userToUpd.getEmail());
    }

    @Override
    @CheckForNull
    public boolean deleteUser(String email) {
        UserEntity entity = userRepository.findById(email)
                .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", email)));
        if (entity.isDeleted()) {
            throw new NotFoundServiceException(String.format("User %s was deleted, contact admin", email));
        }
        entity.setDeleted(true);
        userRepository.save(entity);
        return true;
    }

    @Override
    @CheckForNull
    public boolean addUserCar(String userID, String carId) {
        if (ifUserCarsExist(userID, carId)) {
            throw new ConflictServiceException(String.format("Car with id %s already added", carId));
        }
        UserEntity entity = userRepository.findById(userID)
                .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", userID)));
        List<String> carsIdList = entity.getOwnCars();
        if (carsIdList == null) {
            carsIdList = new ArrayList<>();
            entity.setOwnCars(carsIdList);
        }
        carsIdList.add(carId);
        userRepository.save(entity);
        return true;
    }

    @Override
    @CheckForNull
    public boolean ifUserCarsExist(String userID, String carID) {
        UserEntity entity = userRepository.findById(userID)
                .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", userID)));
        List<String> carsIdList = entity.getOwnCars();
        if (carsIdList == null || carsIdList.isEmpty()) {
            return false;
        }
        return entity.getOwnCars().contains(carID);
    }

    @Override
    @CheckForNull
    public Optional<List<BookedCarDto>> getUserBookedCarsPeriods(String userID) {
        UserEntity entity = userRepository.findById(userID)
                .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", userID)));
        List<BookedPeriodEntity> bookedCars = entity.getBookedCars();
        if (bookedCars == null) {
            return Optional.of(new ArrayList<>());
        }
        List<BookedCarDto> bookedCarsPeriods = bookedCars.stream()
                .map(BookedPeriodMapper.INSTANCE::mapBookedPeriodToBookedCarDto)
                .collect(Collectors.toList());
        return Optional.of(bookedCarsPeriods);
    }

    @Override
    @CheckForNull
    public boolean addBookedPeriodToUserHistory(String userID, BookedPeriodEntity bookedPeriodEntity) {
        UserEntity entity = userRepository.findById(userID)
                .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", userID)));
        List<BookedPeriodEntity> bookedPeriodEntities = entity.getHistory();
        if (bookedPeriodEntities == null) {
            bookedPeriodEntities = new ArrayList<>();
        }
        bookedPeriodEntities.add(bookedPeriodEntity);
        userRepository.save(entity);
        return true;
    }
}
