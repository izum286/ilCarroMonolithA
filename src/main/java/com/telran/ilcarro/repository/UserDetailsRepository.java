package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.UserDetailsEntity;

import java.util.Optional;

public interface UserDetailsRepository {
    Optional<UserDetailsEntity> findById(String user);
}
