package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.model.web.ShortCarDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class CarRepo {

    /**
     * Mock maps instead DB
     * Store ShortCar Entities
     */
    Map<Double, ShortCarDTO> shortCars = new ConcurrentHashMap<>();
    public Map<Double, FullCarDTO> fullCars = new ConcurrentHashMap<>();


    /**
     * returning single short car dto
     * @param carId
     * @return ShortCarDTO
     */
    public ShortCarDTO getSingleCar(UUID carId) {
        ShortCarDTO carDTO = shortCars.get(carId);
        return carDTO;
    }


    /**
     * Provide list of all cars - short representation
     * mock method  - instead searching in DB by filters
     * @return list of short car DTO
     */
    public List<ShortCarDTO> getShortcarList(){
        return new CopyOnWriteArrayList<>(shortCars.values());
    }

    /**
     * Provide full car information to frontend
     * @param carId
     * @return FullCarDTO
     */
    public FullCarDTO getFullCar(UUID carId) {
        return fullCars.get(carId);
    }

    /**
     * Provide list of all cars
     * mock method  - instead searching in DB by filters
     * @return list of full car DTO
     */
    public List<FullCarDTO> getAllCars(){
        return new CopyOnWriteArrayList<>(fullCars.values());
    }
//mocks====mocks====mocks====mocks====mocks====mocks====mocks====mocks====
    /**
     * mock to do logic
     * @param location
     * @param radius
     * @return
     */
    public List<ShortCarDTO> findByLocationAndRadius(String location, double radius){
        return getShortcarList();
    }

    /**
     * mock to do logic
     * @param location, dateFrom, dateTo
     * @return
     */
    public List<ShortCarDTO> findByLocationAndDates
    (String location, String dateFrom, String dateTo){
        return getShortcarList();
    }

    /**
     *
     * @param location
     * @param dateFrom
     * @param dateTo
     * @param priceFrom
     * @param priceTo
     * @return List
     */
    public List<ShortCarDTO> findByLocationDatesPrice
            (String location, String dateFrom, String dateTo,
            double priceFrom,double priceTo ){
        return getShortcarList();
    }


    public void addCar(FullCarDTO car){
        fullCars.put(car.getId(), car);
    }

}
