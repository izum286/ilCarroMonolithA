package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.user.FullUserDTO;
import com.telran.ilcarro.model.web.user.RegUserDTO;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import com.telran.ilcarro.repository.entity.UserEntity;

import java.time.LocalDateTime;

/**
 *
 * DTO <- User- > Entity
 * need to correct logic after the DTO and Entity Objects will be improved
 * @author Konkin Anton
 * @date 18.12.2019
 */
public class UserDTOEntityConverter {
    //TODO update code
    public static UserEntity map (String email, RegUserDTO userDTO) {
        return UserEntity.builder()
                .email(email)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .registrationDate(LocalDateTime.now())
                .isDeleted(false)
                .build();
    }

    //TODO need to check, wtf UserDetailsEntity
    public static UserEntity map (UserDetailsEntity entity, FullUserDTO userDTO) {
        return UserEntity.builder()
                .email(entity.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .userDetails(entity)
                .build();
    }

    public static FullUserDTO map(UserEntity entity) {
        return FullUserDTO.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                //TODO
                .comments(null)
                .bookedCars(null)
                .ownCars(null)
                .history(null)
                .registrationDate(entity.getRegistrationDate())
                .build();
    }
}
