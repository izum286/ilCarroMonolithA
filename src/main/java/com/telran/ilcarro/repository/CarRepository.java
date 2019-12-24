package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.entity.SchedularUsageEntity;
import com.telran.ilcarro.repository.entity.ShortCarEntity;

import java.util.List;
import java.util.UUID;

public interface CarRepository {

    FullCarEntity addCar(FullCarEntity entity);

    FullCarEntity updateCar(FullCarEntity entity);

    boolean deleteCar(UUID id);

    FullCarEntity getCarByIdForUsers(UUID id);

    List<FullCarEntity> ownerGetCars();

    FullCarEntity ownerGetCarById(UUID id);

    List<SchedularUsageEntity>  ownerGetBookedPeriodsByCarId(UUID id);
}
