package com.telran.ilcarro.controller.interfaces;

import com.telran.ilcarro.model.comment.AddCommentDTO;
import com.telran.ilcarro.model.comment.FullCommentDTO;

import java.security.Principal;
import java.util.List;

public interface CommentController {
    List<FullCommentDTO> getLatestComments();
    void postComment(String serialNumber, AddCommentDTO comment, Principal principal);
}
