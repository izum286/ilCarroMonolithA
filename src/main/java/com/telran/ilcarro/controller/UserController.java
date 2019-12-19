package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.user.RegUserDTO;
import com.telran.ilcarro.model.web.user.FullUserDTO;

public interface UserController {
    FullUserDTO registration(RegUserDTO user, String token);
    FullUserDTO login(String token);
    FullUserDTO updateUser(FullUserDTO updUser, String token);
    void deleteUser(String token);




}
