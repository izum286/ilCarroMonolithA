package com.telran.ilcarro.controller.interfaces;

import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;

import java.security.Principal;

/**
 *
 * UserController interface
 *
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface UserController {
    FullUserDTO registration(RegUserDTO user, String token);
    FullUserDTO login(Principal principal);
    FullUserDTO updateUser(UpdUserDTO updUser, String newPassword, Principal principal);
    void deleteUser(Principal principal);




}
