package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.user.FullUserDTO;
import com.telran.ilcarro.model.web.user.RegUserDTO;

import java.util.Optional;
/**
 *
 * UserService interface
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface UserService {
    Optional<FullUserDTO> addUser(String userEmail, RegUserDTO regUser);
    Optional<FullUserDTO> getUser(String email);
    Optional<FullUserDTO> updateUser(String email, FullUserDTO updUser);
    boolean deleteUser(String email);
}
