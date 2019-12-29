package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.CommentEntity;

import java.util.List;

/**
Was used before MongoDB was connected.
 @author Konkin Anton
 */
public interface CommentRepository {
    List<CommentEntity> getLatestComments(Integer num);
    boolean postComment(CommentEntity comment);
}
