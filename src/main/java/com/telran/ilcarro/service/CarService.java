package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.model.web.SchedularUsageDTO;
import com.telran.ilcarro.model.web.ShortCarDTO;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Optional<ShortCarDTO> addCar(FullCarDTO carDTO);

    Optional<ShortCarDTO> updateCar(FullCarDTO carDTO);

    boolean deleteCar(String id);

    Optional<FullCarDTO> getCarByIdForUsers(String id);

    List<FullCarDTO> ownerGetCars();

    Optional<FullCarDTO> ownerGetCarById(String id);

    List<SchedularUsageDTO>  ownerGetBookedPeriodsByCarId(String id);

}