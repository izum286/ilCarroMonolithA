package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.entity.ShortCarEntity;

public class FullCarEntityToShortCarEntityConverter {

    public static ShortCarEntity map(FullCarEntity fullCarEntity){
        return ShortCarEntity.builder()
//                .id(fullCarEntity.getId())
                .image(fullCarEntity.getImage())
                .isRented(fullCarEntity.isRented())
//                .location(fullCarEntity.getLocation())
                .manufacture(fullCarEntity.getManufacture())
                .model(fullCarEntity.getModel())
                .price_per_day(fullCarEntity.getPrice_per_day())
//                .specifications(fullCarEntity.getSpecifications())
                .year(fullCarEntity.getYear())
                .build();
    }
}
