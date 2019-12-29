package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.entity.SchedularUsageEntity;
import com.telran.ilcarro.repository.entity.ShortCarEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CarRepositoryImpl implements CarRepository{

    //TODO что то надо сделать с айдишниками для машин, потому что тут даблы, там стринги, где-то UUID, а в протоколе сериал вообще не в виде UUID
    /**
     * Mock maps instead DB
     * Store ShortCar Entities
     */
    private Map<Double, ShortCarEntity> shortCars = new ConcurrentHashMap<>();
    private Map<Double, FullCarEntity> fullCars = new ConcurrentHashMap<>();

    //TODO заглушки!
    @Override
    public FullCarEntity addCar(FullCarEntity entity){
        fullCars.put(entity.getId(), entity);
        return null;
    }

    @Override
    public FullCarEntity updateCar(FullCarEntity entity) {
        fullCars.put(entity.getId(), entity);
        return null;
    }

    @Override
    public boolean deleteCar(UUID id) {
        return fullCars.remove(id) != null;
    }

    @Override
    public FullCarEntity getCarByIdForUsers(UUID id) {
        FullCarEntity carEntity = fullCars.get(id);
        return carEntity;
    }

    @Override
    public List<FullCarEntity> ownerGetCars() {
        return null;
    }

    @Override
    public FullCarEntity ownerGetCarById(UUID id) {
        return null;
    }

    @Override
    public List<SchedularUsageEntity> ownerGetBookedPeriodsByCarId(UUID id) {
        return null;
    }

}
