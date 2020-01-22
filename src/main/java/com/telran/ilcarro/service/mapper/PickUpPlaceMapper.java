package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.PickUpPlaceDto;
import com.telran.ilcarro.repository.entity.PickUpPlaceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface PickUpPlaceMapper {
    PickUpPlaceMapper INSTANCE = Mappers.getMapper(PickUpPlaceMapper.class);

    PickUpPlaceEntity map(PickUpPlaceDto dto);

    PickUpPlaceDto map(PickUpPlaceEntity entity);
}
