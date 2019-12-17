package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.web.*;
import com.telran.ilcarro.repository.entity.*;

import java.util.ArrayList;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

/**
 * @author vitalii_adler
 * mappers for converting an Entity into an DataTransferObject. Ready for use.
 */

public class mapper {

    public FilterDTO map(FilterEntity entity) {
        return FilterDTO.builder()
                .make(entity.getMake())
                .models(entity.getModels())
                .years(entity.getYears())
                .engines(entity.getEngines())
                .fuel(entity.getFuel())
                .transmissions(entity.getTransmissions())
                .wd(entity.getWd())
                .horsepower(entity.getHorsepower())
                .torque(entity.getTorque())
                .doors(entity.getDoors())
                .seats(entity.getSeats())
                .classs(entity.getClasss())
                .fuelConsumption(entity.getFuelConsumption())
                .build();
    }

    public LocationDTO map(LocationEntity entity) {
        return LocationDTO.builder()
                .country(entity.getCountry())
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .zip(entity.getZip())
                .lat(entity.getLat())
                .lon(entity.getLon())
                .build();
    }

    public SchedularUsageDTO map(SchedularUsageEntity entity) {
        return SchedularUsageDTO.builder()
                .id(entity.getId())
                .userId(UUID.fromString(entity.getUserId()))
                .carId(UUID.fromString(entity.getCarId()))
                .startDate(entity.getStartDate())
                .endDate((entity.getEndDate()))
                .build();
    }

    public SpecsDTO map(SpecsEntity entity) {
        return SpecsDTO.builder()
                .engine(entity.getEngine())
                .fuelCons(entity.getFuelCons())
                .fuelType(entity.getFuelType())
                .transmission(entity.getTransmission())
                .wd(entity.getWd())
                .hp(entity.getHp())
                .torque(entity.getTorque())
                .doors(entity.getDoors())
                .seats(entity.getSeats())
                .clasz(entity.getClasz())
                .build();
    }


    public ShortCarDTO map(ShortCarEntity entity) {
        return ShortCarDTO.builder()
                .id(entity.getId())
                .model(entity.getModel())
                .manufacture(entity.getManufacture())
                .year(entity.getYear())
                .price(entity.getPrice())
                .image(new ArrayList<>(entity.getImage()))
                .specifications(map(entity.getSpecifications()))
                .location(map(entity.getLocation()))
                .isRented(entity.isRented())
                .build();
    }

    public UserDTO map(UserEntity entity) {
        return UserDTO.builder()
                .id(UUID.fromString(entity.getUuid()))
                .driverLicense(entity.getDriverLicense())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .city(entity.getCity())
                .street(entity.getStreet())
                .phone(entity.getPhone())
                .usages(entity.getUsages()
                        .stream()
                        .map(this::map)
                        .collect(toList()))
                .build();
    }
}
