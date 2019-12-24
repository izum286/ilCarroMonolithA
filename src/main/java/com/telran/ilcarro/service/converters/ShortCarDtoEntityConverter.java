package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.ShortCarDTO;
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
                .price(entity.getPrice())
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
                .price(dto.getPrice())
                .specifications(SpecificationDtoEntityConverter.map(dto.getSpecifications()))
                .year(dto.getYear())
                .build();
    }
}
