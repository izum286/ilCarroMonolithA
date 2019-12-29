package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.user.FullUserDTO;
import com.telran.ilcarro.repository.entity.UserEntity;

public interface DtoFabricService {
    FullUserDTO getFullUserDto(UserEntity user);
}
