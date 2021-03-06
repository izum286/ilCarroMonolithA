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

import java.util.ArrayList;

@Mapper(uses = {CommentMapper.class, BookedPeriodMapper.class}
        ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "email", target = "email")
    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "comments", expression = "java(new ArrayList<>())")
    UserEntity map (String email, RegUserDTO regUserDTO);

    @Mapping(source = "userDetailsEntity.email", target = "email")
    @Mapping(target = " ownCars", ignore = true)
    UserEntity map (UserDetailsEntity userDetailsEntity, FullUserDTO fullUserDTO);

    /**
     * Update current User information according UpdUserDto
     * @param currUser - current User entity
     * @param userDTO - DTO with updates
     */
    void updUserInfo(@MappingTarget UserEntity currUser, UpdUserDTO userDTO);

    @Mapping(source = "firstName", target = "first_name")
    @Mapping(source = "lastName", target = "second_name")
    @Mapping(source = "registrationDate", target = "registration_date")
//    @Mapping(target = "own_cars",  source = "ownCars")
    @Mapping(target = "booked_car", source = "bookedCars")
    @Mapping(target = "history", source = "history")
//    @Mapping(target = "own_cars",  expression = "java(new ArrayList<>())")
//    @Mapping(target = "booked_car", expression = "java(new ArrayList<>())")
//    @Mapping(target = "history", expression = "java(new ArrayList<>())")
    @Mapping(target = "photo", source = "photo", defaultValue = "https://a.d-cd.net/4e0c9b9s-1920.jpg")
    FullUserDTO map(UserEntity userEntity);
}
