package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.FeatureDto;
import com.telran.ilcarro.repository.entity.FeatureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CommentMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FeatureMapper {
    FeatureMapper INSTANCE = Mappers.getMapper(FeatureMapper.class);

    FeatureEntity map(FeatureDto dto);

    FeatureDto map(FeatureEntity entity);

}
