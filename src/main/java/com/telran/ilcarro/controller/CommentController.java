package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.comment.AddCommentDTO;
import com.telran.ilcarro.model.web.comment.FullCommentDTO;

public interface CommentController {
    FullCommentDTO getLatestComments(Integer num);
    void postComment(String serialNumber, AddCommentDTO comment);
}
