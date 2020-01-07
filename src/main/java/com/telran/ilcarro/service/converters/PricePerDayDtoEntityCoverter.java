package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.car.PricePerDayDto;
import com.telran.ilcarro.repository.entity.PricePerDayEntity;

/**
 *
 * !!!UNUSED!!!
 *
 * PricePerDayEntity <---> PricePerDayDto
 * Implementation of convertation logic for Converters with PricePerDayEntity and PricePerDayDto
 * @author Gor Aleks
 * .12.2019
 */
@Deprecated
public class PricePerDayDtoEntityCoverter {

    public static PricePerDayDto map(PricePerDayEntity entity){
        return PricePerDayDto.builder()
                .currency(entity.getCurrency())
                .value(entity.getValue())
                .build();
    }

    public static PricePerDayEntity map(PricePerDayDto dto){
        return PricePerDayEntity.builder()
                .currency(dto.getCurrency())
                .value(dto.getValue())
                .build();
    }
}
