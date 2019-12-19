package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * CRUD UserEntityRepository implementation
 * ----------------
 * ONLY FOR TESTING
 * ----------------
 * @author Konkin Anton
 * @date 19.12.2019
 */
@Repository
public class UserEntityRepositoryImpl implements UserEntityRepository {
    Map<String, UserEntity> repo = new ConcurrentHashMap<>();

    @Override
    public UserEntity getUserByEmail(String email) {
        UserEntity entity = repo.get(email);
        if (entity == null || entity.isDeleted()) {
            throw new NotFoundRepositoryException(String.format("User %s not found", email));
        }
        return entity;
    }

    @Override
    public UserEntity addUser(UserEntity entity) {
        UserEntity user = repo.putIfAbsent(entity.getEmail(), entity);
        if (user != null) {
            throw new ConflictRepositoryException(String.format("User %s already exist", entity.getEmail()));
        }
        return entity;
    }

    @Override
    public UserEntity updateUser(UserEntity entity) {
        if (repo.containsKey(entity.getEmail())) {
            repo.put(entity.getEmail(), entity);
        }
        return entity;
    }

    @Override
    public boolean deleteUser(String email) {
        if (!repo.containsKey(email)) {
            throw new NotFoundRepositoryException(String.format("User %s not found", email));
        }
        repo.computeIfPresent(email, (key, entity) -> {
            entity.setDeleted(true);
            return entity;
        });
        return true;
    }
}
