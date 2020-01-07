package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.comment.FullCommentDTO;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.repository.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CommentMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    FullCarEntity map(AddUpdateCarDtoRequest dto);

    FullCarDTOResponse map(FullCarEntity entity);

}
