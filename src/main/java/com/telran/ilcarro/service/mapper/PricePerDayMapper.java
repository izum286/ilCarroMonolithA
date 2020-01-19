package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.PricePerDayDto;
import com.telran.ilcarro.repository.entity.PricePerDayEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PricePerDayMapper {
    PricePerDayMapper INSTANCE = Mappers.getMapper(PricePerDayMapper.class);

    PricePerDayEntity map(PricePerDayDto dto);

    PricePerDayDto map(PricePerDayEntity entity);
}
