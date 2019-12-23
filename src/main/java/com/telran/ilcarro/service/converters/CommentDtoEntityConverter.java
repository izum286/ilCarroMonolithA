package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.comment.AddCommentDTO;
import com.telran.ilcarro.model.web.comment.FullCommentDTO;
import com.telran.ilcarro.repository.entity.CommentEntity;
import com.telran.ilcarro.repository.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommentDtoEntityConverter {
    public static FullCommentDTO map(CommentEntity comment, UserEntity user) {
        return FullCommentDTO.builder()
                .first_name(user.getFirstName())
                .second_name(user.getLastName())
                .post_date(comment.getPostDateTime())
                .post(comment.getPost())
                .photo(user.getPhoto())
                .build();
    }

    public static CommentEntity map(AddCommentDTO comment, String serialNumber, String email) {
        return CommentEntity.builder()
                .id(UUID.randomUUID().toString())
                .ownerEmail(email)
                .postDateTime(LocalDateTime.now())
                .post(comment.getMessage())
                .serialNumber(serialNumber)
                .build();
    }
}
