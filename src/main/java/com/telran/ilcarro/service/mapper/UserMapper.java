package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CommentMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "email", target = "email")
    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDateTime.now())")
    UserEntity map (String email, RegUserDTO regUserDTO);

    @Mapping(source = "userDetailsEntity.email", target = "email")
    UserEntity map (UserDetailsEntity userDetailsEntity, FullUserDTO fullUserDTO);

    /**
     * Update current User information according UpdUserDto
     * @param currUser - current User entity
     * @param userDTO - DTO with updates
     * @return new UserEntity
     */
    @Mapping(source = "userDTO.firstName", target = "firstName")
    @Mapping(source = "userDTO.lastName", target = "lastName")
    @Mapping(source = "userDTO.photo", target = "photo")
    @Mapping(source = "currUser.email", target = "email")
    @Mapping(source = "currUser.driverLicense", target = "driverLicense")
    @Mapping(source = "currUser.location", target = "location")
    @Mapping(source = "currUser.phone", target = "phone")
//    @Mapping(source = "currUser.isDeleted", target = "isDeleted")
    @Mapping(source = "currUser.registrationDate", target = "registrationDate")
    @Mapping(source = "currUser.comments", target = "comments")
    @Mapping(source = "currUser.usages", target = "usages")

    UserEntity updUserInfo(UserEntity currUser, UpdUserDTO userDTO);
}
