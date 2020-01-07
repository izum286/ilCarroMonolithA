package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.car.probably_unused.LocationDTO;
import com.telran.ilcarro.repository.entity.LocationEntity;

/**
 *
 * !!!UNUSED!!!
 *
 * LocationEntity <---> LocationDto
 * Implementation of convertation logic for Converters with FullCarEntity and ShortCarEntity
 * @author Gor Aleks
 * 24.12.2019
 */
@Deprecated
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
