package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.user.RegUserDTO;
import com.telran.ilcarro.model.web.user.FullUserDTO;
import com.telran.ilcarro.model.web.user.UpdUserDTO;

/**
 *
 * UserController interface
 *
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface UserController {
    FullUserDTO registration(RegUserDTO user, String token);
    FullUserDTO login(String token);
    FullUserDTO updateUser(UpdUserDTO updUser, String token, String newPassword);
    void deleteUser(String token);




}
