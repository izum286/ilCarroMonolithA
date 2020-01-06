package com.telran.ilcarro.service;

import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class DtoFabricServiceImpl implements DtoFabricService{

    @Override
    public FullUserDTO getFullUserDto(UserEntity user) {
        FullUserDTO userDTO = UserMapper.INSTANCE.map(user);
        //TODO add bookedCars field
        //TODO add history field
        return userDTO;
    }
}
