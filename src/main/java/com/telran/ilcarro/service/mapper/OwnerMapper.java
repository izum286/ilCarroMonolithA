package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.repository.entity.OwnerEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CommentMapper.class)
public interface OwnerMapper {
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    @Mapping(target = "firstName", source = "firstName", defaultValue = "none")
    @Mapping(target = "lastName", source = "lastName", defaultValue = "none")
    OwnerDtoResponse map(OwnerEntity entity);

    OwnerEntity map(UserEntity entity);
}
