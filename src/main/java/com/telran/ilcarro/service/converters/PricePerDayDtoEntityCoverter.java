package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.PricePerDayDto;
import com.telran.ilcarro.repository.entity.PricePerDayEntity;

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
