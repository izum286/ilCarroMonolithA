package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.repository.entity.OwnerEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CommentMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OwnerMapper {
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    OwnerDtoResponse map(OwnerEntity entity);
    OwnerEntity map(UserEntity entity);
}
