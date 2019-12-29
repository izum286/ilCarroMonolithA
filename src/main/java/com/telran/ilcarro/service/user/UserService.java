package com.telran.ilcarro.service.user;

import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;

import java.util.Optional;
/**
 *
 * UserService interface
 * @author Konkin Anton
 * @date 19.12.2019
 */
public interface UserService {
    Optional<FullUserDTO> addUser(String userEmail, RegUserDTO regUser) throws ConflictServiceException;
    Optional<FullUserDTO> getUser(String email) throws NotFoundServiceException;
    Optional<FullUserDTO> updateUser(String email, UpdUserDTO updUser) throws NotFoundServiceException, ConflictServiceException;
    boolean deleteUser(String email) throws NotFoundServiceException;
}
