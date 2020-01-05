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
    UserEntity map (String email, RegUserDTO regUserDTO);

    @Mapping(source = "userDetailsEntity.email", target = "email")
    UserEntity map (UserDetailsEntity userDetailsEntity, FullUserDTO fullUserDTO);

    /**
     * Update current User information according UpdUserDto
     * @param currUser - current User entity
     * @param userDTO - DTO with updates
     * @return new UserEntity
     */

    UserEntity updUserInfo(UserEntity currUser, UpdUserDTO userDTO);
}
