package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.entity.SchedularUsageEntity;
import com.telran.ilcarro.repository.entity.ShortCarEntity;

import java.util.List;
import java.util.UUID;

public interface CarRepository {

    FullCarEntity addCar(AddUpdateCarDtoRequest carToAdd);

    FullCarEntity updateCar(AddUpdateCarDtoRequest carToUpdate);

    boolean deleteCar(UUID id);

    FullCarEntity getCarByIdForUsers(UUID id);

    List<FullCarEntity> ownerGetCars();

    FullCarEntity ownerGetCarById(UUID id);

    List<BookedPeriodDto>  ownerGetBookedPeriodsByCarId(String carId);
}
