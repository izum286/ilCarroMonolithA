package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.comment.AddCommentDTO;
import com.telran.ilcarro.model.web.comment.FullCommentDTO;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;

import java.util.List;

public interface CommentService {
    List<FullCommentDTO> getLatestComments(Integer num) throws NotFoundServiceException;
    boolean postComment(String serialNumber, String ownerEmail, AddCommentDTO comment) throws ConflictServiceException;
}
