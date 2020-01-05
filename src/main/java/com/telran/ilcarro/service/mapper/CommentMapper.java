package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.comment.AddCommentDTO;
import com.telran.ilcarro.model.comment.FullCommentDTO;
import com.telran.ilcarro.repository.entity.CommentEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "postDate", source = "comment.postDateTime")
    @Mapping(target = "secondName", source = "user.lastName")
    FullCommentDTO map(CommentEntity comment, UserEntity user);

    @Mapping(target = "postDateTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "ownerEmail", source = "email")
    CommentEntity map(AddCommentDTO comment, String serialNumber, String email);


}
