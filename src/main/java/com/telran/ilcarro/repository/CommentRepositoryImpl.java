package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.CommentEntity;
import com.telran.ilcarro.repository.entity.FeedbackEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.repository.exception.RepositoryException;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class CommentRepositoryImpl implements CommentRepository{

    Map<String, CommentEntity> commentEntities = new ConcurrentHashMap<>();

    @Override
    public List<CommentEntity> getLatestComments(Integer num) {
        if (num < 0) {
            throw new RepositoryException(String.format("Illegal argument %d must be bigger then 0", num));
        }
        if (commentEntities.isEmpty()) {
            throw new NotFoundRepositoryException("Comments not found");
        }
        return commentEntities.values().stream()
                .sorted(Comparator.comparing(CommentEntity::getPostDateTime).reversed())
                .limit(num)
                .collect(Collectors.toList());
    }

    @Override
    public boolean postComment(CommentEntity comment) {
        try {
            if(commentEntities.putIfAbsent(comment.getId(), comment) != null) {
                throw new ConflictRepositoryException(String.format("Comment with id %s already exist", comment.getId()));
            }
            return true;
        } catch (Throwable t) {
            throw new RepositoryException(t.getMessage(), t.getCause());
        }

    }
}
