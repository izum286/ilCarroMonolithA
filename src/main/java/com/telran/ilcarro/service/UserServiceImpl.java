package com.telran.ilcarro.service;


import com.telran.ilcarro.model.web.user.FullUserDTO;
import com.telran.ilcarro.model.web.user.RegUserDTO;
import com.telran.ilcarro.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public UserEntity addUser(RegUserDTO regUser) {
        return null;
    }

    @Override
    public UserEntity getUser(String email) {
        return null;
    }

    @Override
    public UserEntity updateUswer(FullUserDTO updUser) {
        return null;
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }
}
