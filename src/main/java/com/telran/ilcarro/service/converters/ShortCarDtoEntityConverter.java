package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.car.probably_unused.ShortCarDTO;
import com.telran.ilcarro.repository.entity.ShortCarEntity;

public class ShortCarDtoEntityConverter {

    public static ShortCarDTO map(ShortCarEntity entity){
        return ShortCarDTO.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .isRented(entity.isRented())
                .location(LocationDtoEntityConverter.map(entity.getLocation()))
                .manufacture(entity.getManufacture())
                .model(entity.getModel())
                .price_per_day(PricePerDayDtoEntityCoverter.map(entity.getPrice_per_day()))
                .specifications(SpecificationDtoEntityConverter.map(entity.getSpecifications()))
                .year(entity.getYear())
                .build();
    }

    public static ShortCarEntity map(ShortCarDTO dto){
        return ShortCarEntity.builder()
                .id(dto.getId())
                .image(dto.getImage())
                .isRented(dto.isRented())
                .location(LocationDtoEntityConverter.map(dto.getLocation()))
                .manufacture(dto.getManufacture())
                .model(dto.getModel())
                .price_per_day(PricePerDayDtoEntityCoverter.map(dto.getPrice_per_day()))
                .specifications(SpecificationDtoEntityConverter.map(dto.getSpecifications()))
                .year(dto.getYear())
                .build();
    }
}
