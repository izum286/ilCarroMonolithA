package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.FullCarDTOResponse;
import com.telran.ilcarro.model.web.SchedularUsageDTO;
import com.telran.ilcarro.model.web.ShortCarDTO;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Optional<ShortCarDTO> addCar(FullCarDTOResponse carDTO);

    Optional<ShortCarDTO> updateCar(FullCarDTOResponse carDTO);

    boolean deleteCar(String id);

    Optional<FullCarDTOResponse> getCarByIdForUsers(String id);

    List<FullCarDTOResponse> ownerGetCars();

    Optional<FullCarDTOResponse> ownerGetCarById(String id);

    List<SchedularUsageDTO>  ownerGetBookedPeriodsByCarId(String id);

}