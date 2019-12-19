package com.telran.ilcarro.service;


import com.telran.ilcarro.model.web.user.FullUserDTO;
import com.telran.ilcarro.model.web.user.RegUserDTO;
import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.repository.exception.RepositoryException;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.telran.ilcarro.service.converters.UserDTOEntityConverter.map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserEntityRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public Optional<FullUserDTO> addUser(String email, RegUserDTO regUser) {
        try {
            UserEntity entity = userRepository.addUser(map(email, regUser));
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
            UserEntity entity = userRepository.getUserByEmail(email);
            return Optional.of(map(entity));
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    @Override
    public Optional<FullUserDTO>  updateUser(String email, FullUserDTO updUser) {
        try {
            UserDetailsEntity userDetailsEntity = userDetailsRepository.findById(email).orElseThrow();
            UserEntity entity = userRepository.updateUser(map(userDetailsEntity, updUser));
            return Optional.of(map(entity));
        } catch (ConflictRepositoryException ex) {
            throw new ConflictServiceException(ex.getMessage(), ex.getCause());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    @Override
    public boolean deleteUser(String email) {
        try {
            UserEntity entity = userRepository.getUserByEmail(email);
            if (entity.isDeleted()) {
                throw new NotFoundServiceException(String.format("User %s was deleted, contact admin", email));
            }
            entity.setDeleted(true);
            return true;
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }
}
