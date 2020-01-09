package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CommentMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    FullCarEntity map(AddUpdateCarDtoRequest dto);

    FullCarDTOResponse map(FullCarEntity entity);

}
