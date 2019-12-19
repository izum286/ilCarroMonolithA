package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.user.FullUserDTO;
import com.telran.ilcarro.model.web.user.RegUserDTO;
import com.telran.ilcarro.repository.entity.UserEntity;

public interface UserService {
    UserEntity addUser(RegUserDTO regUser);
    UserEntity getUser(String email);
    UserEntity updateUswer(FullUserDTO updUser);
    boolean deleteUser(String email);
}
