package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.comment.AddCommentDTO;
import com.telran.ilcarro.model.web.comment.FullCommentDTO;

import java.util.List;

public interface CommentService {
    List<FullCommentDTO> getLatestComments(Integer num);
    boolean postComment(String serialNumber, String ownerEmail, AddCommentDTO comment);
}
