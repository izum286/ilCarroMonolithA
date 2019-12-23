package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.CommentEntity;

import java.util.List;

public interface CommentRepository {
    List<CommentEntity> getLatestComments(Integer num);
    boolean postComment(CommentEntity comment);
}
