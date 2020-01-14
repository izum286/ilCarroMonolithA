package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommentMapper.class, BookedPeriodMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "email", target = "email")
    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDateTime.now())")
    UserEntity map (String email, RegUserDTO regUserDTO);

    @Mapping(source = "userDetailsEntity.email", target = "email")
    @Mapping(target = "ownCars", ignore = true)
    UserEntity map (UserDetailsEntity userDetailsEntity, FullUserDTO fullUserDTO);

    /**
     * Update current User information according UpdUserDto
     * @param currUser - current User entity
     * @param userDTO - DTO with updates
     */
    void updUserInfo(@MappingTarget UserEntity currUser, UpdUserDTO userDTO);

    @Mapping(target = "ownCars", ignore = true)
    @Mapping(target = "bookedCars", ignore = true)
    @Mapping(target = "history", ignore = true)
    FullUserDTO map(UserEntity userEntity);
}
