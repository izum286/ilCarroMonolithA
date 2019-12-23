package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.model.web.SchedularUsageDTO;
import com.telran.ilcarro.model.web.ShortCarDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Override
    public Optional<ShortCarDTO> addCar(FullCarDTO carDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<ShortCarDTO> updateCar(FullCarDTO carDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteCar(String id) {
        return false;
    }

    @Override
    public Optional<FullCarDTO> getCarByIdForUsers(String id) {
        return Optional.empty();
    }

    @Override
    public List<FullCarDTO> ownerGetCars() {
        return null;
    }

    @Override
    public Optional<FullCarDTO> ownerGetCarById(String id) {
        return Optional.empty();
    }

    @Override
    public List<SchedularUsageDTO> ownerGetBookedPeriodsByCarId(String id) {
        return null;
    }
}
