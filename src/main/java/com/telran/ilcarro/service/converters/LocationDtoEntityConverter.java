package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.LocationDTO;
import com.telran.ilcarro.repository.entity.LocationEntity;

public class LocationDtoEntityConverter {

    public static LocationDTO map(LocationEntity entity){
        return LocationDTO.builder()
                .city(entity.getCity())
                .country(entity.getCountry())
                .isVehicle(entity.isVehicle())
                .lat(entity.getLat())
                .lon(entity.getLon())
                .state(entity.getState())
                .street(entity.getStreet())
                .zip(entity.getZip())
                .build();
    }

    public static LocationEntity map(LocationDTO dto){
        return LocationEntity.builder()
                .city(dto.getCity())
                .country(dto.getCountry())
                .isVehicle(dto.isVehicle())
                .lat(dto.getLat())
                .lon(dto.getLon())
                .state(dto.getState())
                .street(dto.getStreet())
                .zip(dto.getZip())
                .build();
    }
}