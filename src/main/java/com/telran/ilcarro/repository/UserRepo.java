package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.UserDetailsEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo implements UserDetailsRepository{
    Map<String, UserDetailsEntity> repository = new ConcurrentHashMap<>();

    @Override
    public Optional<UserDetailsEntity> findById(String email) {
        UserDetailsEntity entity = repository.get(email);
        return entity != null ? Optional.of(entity) : Optional.empty();
    }

    @Override
    public boolean existsById(String email) {
        return repository.containsKey(email);
    }

    @Override
    public boolean save(UserDetailsEntity entity) {
        repository.putIfAbsent(entity.getEmail(), entity);
        return false;
    }
//    Map<UUID, UserDTO> repo = new ConcurrentHashMap<>();
//    Map<String, UserDTO> idRepo = new ConcurrentHashMap<>();
//    public UserDTO getSingleUser(UUID userId) {
//        return repo.get(userId);
//    }
//
//    public boolean existsById(String email) {
//        return idRepo.containsKey(email);
//    }
//
//    public void addUser(UserDTO user) {
//        repo.put(user.getId(), user);
//        idRepo.put(user.getEmail(), user);
//    }
}
