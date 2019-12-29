package com.telran.ilcarro.service.car;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.car.probably_unused.SchedularUsageDTO;
import com.telran.ilcarro.model.car.probably_unused.ShortCarDTO;
import com.telran.ilcarro.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<FullCarDTOResponse> addCar(AddUpdateCarDtoRequest carDTO);
    Optional<FullCarDTOResponse> updateCar(AddUpdateCarDtoRequest carDTO);
    boolean deleteCar(String carId);
    List<FullCarDTOResponse> ownerGetCars();
    Optional<FullCarDTOResponse> getCarById(String carId);
    List<BookedPeriodDto> getBookedPeriodsByCarId(String carId);
    BookedPeriodDto makeReservation(String carId, BookRequestDTO dto);
    Optional<UserEntity> getUserByCarId(String carId);
    List<CarStatDto> getCarStatById(String carId);


}