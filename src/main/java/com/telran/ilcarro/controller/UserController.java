package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.user.RegUserDTO;
import com.telran.ilcarro.model.web.user.FullUserDTO;
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
    FullUserDTO updateUser(FullUserDTO updUser, String token);
    void deleteUser(String token);




}
