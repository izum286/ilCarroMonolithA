package com.telran.ilcarro.service.user;


import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.model.user.RegUserDTO;
import com.telran.ilcarro.model.user.UpdUserDTO;
import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.service.DtoFabricService;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.telran.ilcarro.service.converters.UserDTOEntityConverter.map;
/**
 *
 * UserServiceImpl implementation of UserService
 * @see UserService
 * @author Konkin Anton
 * @date 19.12.2019
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserEntityRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    DtoFabricService dtoFabric;

    @Override
    public Optional<FullUserDTO> addUser(String email, RegUserDTO regUser) {
        try {
            UserEntity entity = userRepository.save(map(email, regUser));
            return Optional.of(map(entity));
        } catch (ConflictRepositoryException ex) {
            throw new ConflictServiceException(ex.getMessage(), ex.getCause());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    @Override
    public Optional<FullUserDTO> getUser(String email) {
        try {
            UserEntity entity = userRepository.findById(email)
                    .orElseThrow(()-> new NotFoundServiceException(String.format("User %s not found", email)));
            return Optional.of(dtoFabric.getFullUserDto(entity));
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    @Override
    public Optional<FullUserDTO>  updateUser(String email, UpdUserDTO updUser) {
        try {
            if (!userDetailsRepository.existsById(email)) {
                throw new NotFoundServiceException(String.format("User %s not found", email));
            }
            UserEntity userToUpd = userRepository.findById(email)
                    .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", email)));
            UserEntity entity = userRepository.save(map(userToUpd, updUser));
            return Optional.of(map(entity));
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    @Override
    public boolean deleteUser(String email) {
        try {
            UserEntity entity = userRepository.findById(email)
                    .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", email)));
            if (entity.isDeleted()) {
                throw new NotFoundServiceException(String.format("User %s was deleted, contact admin", email));
            }
            entity.setDeleted(true);
            userRepository.save(entity);
            return true;
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }
}
