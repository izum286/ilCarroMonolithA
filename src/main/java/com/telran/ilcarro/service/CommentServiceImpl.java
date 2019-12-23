package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.comment.AddCommentDTO;
import com.telran.ilcarro.model.web.comment.FullCommentDTO;
import com.telran.ilcarro.repository.CommentRepository;

import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.telran.ilcarro.service.converters.CommentDtoEntityConverter.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserEntityRepository userEntityRepository;
    @Override
    public List<FullCommentDTO> getLatestComments(Integer num) {
        try {
            return commentRepository.getLatestComments(num).stream()
                    .map(comment -> {
                        UserEntity user = userEntityRepository.getUserByEmail(comment.getOwnerEmail());
                        return map(comment, user);
                    })
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    @Override
    public boolean postComment(String serialNumber, String ownerEmail, AddCommentDTO comment) {
        try {
            //TODO chek if serialNumber is exists from carRepo
            return commentRepository.postComment(map(comment, serialNumber, ownerEmail));
        } catch (ConflictRepositoryException ex) {
            throw new ConflictServiceException(ex.getMessage());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }
}
