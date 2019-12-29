package com.telran.ilcarro.service;

import com.telran.ilcarro.model.comment.FullCommentDTO;
import com.telran.ilcarro.model.user.FullUserDTO;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.service.converters.CommentDtoEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telran.ilcarro.service.converters.UserDTOEntityConverter.map;
@Service
public class DtoFabricServiceImpl implements DtoFabricService{
    @Autowired
    UserEntityRepository userEntityRepository;

    @Override
    public FullUserDTO getFullUserDto(UserEntity user) {
        FullUserDTO userDTO = map(user);
        if (user.getComments() != null) {
            List<FullCommentDTO> dtoComments = user.getComments().stream()
                    .map(comment -> CommentDtoEntityConverter.map(comment, userEntityRepository.findById(comment.getOwnerEmail()).get()))
                    .collect(Collectors.toList());
            userDTO.setComments(dtoComments);
        }
        return userDTO;
    }
}
