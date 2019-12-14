package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.web.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo {
    Map<UUID, UserDTO> repo = new ConcurrentHashMap<>();
    Map<String, UserDTO> idRepo = new ConcurrentHashMap<>();
    public UserDTO getSingleUser(UUID userId) {
        return repo.get(userId);
    }

    public boolean existsById(String email) {
        return idRepo.containsKey(email);
    }

    public void addUser(UserDTO user) {
        repo.put(user.getId(), user);
        idRepo.put(user.getEmail(), user);
    }
}
