package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.FullCarDTOResponse;
import com.telran.ilcarro.repository.entity.FullCarEntity;

public class FullCarDtoEntityConverter {

    public static FullCarDTOResponse map(FullCarEntity entity) {
        return FullCarDTOResponse.builder()
                .serial_number(entity.getId())
                .about(entity.getAbout())
                .features(entity.getFeatures())
                .feedBacks(entity.getFeedBacks())
                .image(entity.getImage())
                .isRented(entity.isRented())
                .location(entity.getLocation())
                .manufacture(entity.getManufacture())
                .model(entity.getModel())
                .owner(entity.getOwner())
                .price(entity.getPricePerDay())
                .specifications(SpecificationDtoEntityConverter.map(entity.getSpecifications()))
                .trips(entity.getTrips())
                .usages(SchedularUsageListDtoEntityConverter.mapListToDTO(entity.getUsages()))
                .year(entity.getYear())
                .build();
    }

    public static FullCarEntity map(FullCarDTOResponse dto) {
        return FullCarEntity.builder()
                .id(dto.getId())
                .about(dto.getAbout())
                .features(dto.getFeatures())
                .feedBacks(dto.getFeedBacks())
                .image(dto.getImage())
                .isRented(dto.isRented())
                .location(dto.getLocation())
                .manufacture(dto.getManufacture())
                .model(dto.getModel())
                .owner(dto.getOwner())
                .pricePerDay(dto.getPrice())
                .specifications(SpecificationDtoEntityConverter.map(dto.getSpecifications()))
                .trips(dto.getTrips())
                .usages(SchedularUsageListDtoEntityConverter.mapListToEntities(dto.getUsages()))
                .year(dto.getYear())
                .build();
    }
}
