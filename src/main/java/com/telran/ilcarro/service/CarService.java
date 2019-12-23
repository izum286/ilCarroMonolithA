package com.telran.ilcarro.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.ilcarro.service.exceptions.GlobalHandler;
import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.model.web.SchedularUsageDTO;
import com.telran.ilcarro.model.web.ShortCarDTO;
import com.telran.ilcarro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    CarRepo carRepo;

    @Autowired
    SchedularUsagesRepo schedularUsagesRepo;

    @Autowired
    SchedularActiveUsagesRepo activeUsagesRepo;

    @Autowired
    MapperService mapperService;

    @Autowired
    GlobalHandler globalHandler;

    @Autowired
//    UserDetailsRepository userRepo;
    UserRepo userRepo;

    ObjectMapper mapper = new ObjectMapper();
    /**
     * @param carId
     * @return JSON parsed from ShortCarDto using Json ObjectMapper
     * @throws JsonProcessingException
     */
    public String getSingleCar(UUID carId) throws JsonProcessingException {
        return mapper
                .writeValueAsString
                        (carRepo.getSingleCar(carId));
    }


    /**
     * getting list of the first 3 most popular cars
     * @return
     * @throws JsonProcessingException
     */
    public String getPopularCar () throws JsonProcessingException {
        return  mapper.writeValueAsString(
                carRepo.getAllCars()
                .stream()
                        .sorted(Comparator.comparingInt(FullCarDTO::getTrips))
                        .limit(3).collect(Collectors.toList())
        );
    }


    public String getSingleFullCar(UUID carId) {
        return mapperService.map(carRepo.getSingleCar(carId));
    }

    public String findByLocationAndArea(String location, double area) {
        return mapperService.map(
                carRepo.findByLocationAndRadius(location, area));

    }

    public String findByLocationAndDates(String location, String dateFrom, String dateTo) {
        return mapperService.map(
                carRepo.findByLocationAndDates(location, dateFrom, dateTo));
    }

    public String findByLocationDatePrice(String location, String from, String to, double pricefrom, double priceTo) {
        return mapperService.map(
                carRepo.findByLocationDatesPrice(location, from, to, pricefrom, priceTo)
        );
    }

    /**
     * adding car for service
     *
     * @param data
     * @return boolean
     */
    public boolean addCar(String data) {
        globalHandler.handleEmptyData(data);
        FullCarDTO car = mapperService.map(data, FullCarDTO.class);
        if (carRepo.fullCars.containsKey(car.getId())) {
            carRepo.addCar(car);
            return true;
        } return false;
    }

    /**
     * rent car
     * mark selected car as rented, aad rent record to selected car
     * adding renRecord to repos:
     * SchedularUsages (for history)
     * UserRepo user - for history
     * SchedularActiveUsages - for fast operations
     * @param data
     * @return
     */
    public boolean rentCar(String data) {
        globalHandler.handleEmptyData(data);
        SchedularUsageDTO tmp = mapperService.map(data, SchedularUsageDTO.class);
        FullCarDTO selected = carRepo.getFullCar(tmp.getCarId());
        if (!selected.isRented() && checkIfAvailable(data)) {
            SchedularUsageDTO rentRecord = new SchedularUsageDTO()
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .carId(tmp.getCarId())
                    .startDate(tmp.getStartDate())
                    .endDate(tmp.getEndDate())
                    .userId(tmp.getUserId())
                    .build();

            selected.setUsages(rentRecord);
            selected.setRented(true);
            //TODO connect to UserEntityRepository
//            userRepo.getSingleUser(tmp.getUserId()).setUsages(rentRecord);
            //TODO -------------------------------
            activeUsagesRepo.setEntity(tmp.getCarId(), rentRecord);
            return true;
        }
        return false;
    }

    /**
     * returning car  from customer
     * removing rent record from repo of active records
     * @param carId
     * @return true or false
     */
    public boolean returnCar(UUID carId) {
        ShortCarDTO selected = carRepo.getSingleCar(carId);
        if(selected.isRented()){
            selected.setRented(false);
            activeUsagesRepo.removeEntity(carId);
            return true;
        }
        return false;
    }


    /**
     *
     * @param data
     * @return false - if auto not available between current dates
     * @return true - if auto available between current dates
     */
    public boolean checkIfAvailable(String data) {
        globalHandler.handleEmptyData(data);
        SchedularUsageDTO tmp = mapperService.map(data, SchedularUsageDTO.class);
        SchedularUsageDTO selected = schedularUsagesRepo.getEntity(tmp.getCarId());
        if(selected==null){
            return true;
        }
        return selected.getStartDate().isAfter(tmp.getEndDate()) || selected.getEndDate().isBefore(tmp.getStartDate());
    }

}
