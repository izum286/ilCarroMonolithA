package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.user.FullUserDTO;
import com.telran.ilcarro.repository.entity.UserEntity;

/**
 * @author Anton Konkin
 */
public interface DtoFabricService {
    /**
     * Convert and compile user entity to user fullDto
     * @param user Entity
     * @return FullUserDTO
     */
    FullUserDTO getFullUserDto(UserEntity user);
}
