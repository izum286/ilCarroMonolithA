package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.comment.AddCommentDTO;
import com.telran.ilcarro.model.web.comment.FullCommentDTO;

import java.util.List;

public interface CommentController {
    List<FullCommentDTO> getLatestComments(Integer num);
    void postComment(String serialNumber, String token, AddCommentDTO comment);
}
