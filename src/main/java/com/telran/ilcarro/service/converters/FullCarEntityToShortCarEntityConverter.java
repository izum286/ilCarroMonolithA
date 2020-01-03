package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.entity.ShortCarEntity;

/**
 *
 * !!!UNUSED!!!
 *
 * FullCarEntity ---> ShortCarEntity
 * Implementation of convertation logic for CarService with FullCarEntity and ShortCarEntity
 * @author Gor Aleks
 * @date 24.12.2019
 */

public class FullCarEntityToShortCarEntityConverter {

    public static ShortCarEntity map(FullCarEntity fullCarEntity){
        return ShortCarEntity.builder()
//                .id(fullCarEntity.getId())
                .image(fullCarEntity.getImage())
                //.isRented(fullCarEntity.isRented())
//                .location(fullCarEntity.getLocation())
                .manufacture(fullCarEntity.getManufacture())
                .model(fullCarEntity.getModel())
                .price_per_day(fullCarEntity.getPricePerDay())
//                .specifications(fullCarEntity.getSpecifications())
//                .year(fullCarEntity.getYear())
                .build();
    }
}
