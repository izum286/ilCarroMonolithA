package com.telran.ilcarro.service.comment;

import com.telran.ilcarro.annotaion.CheckForNull;
import com.telran.ilcarro.model.comment.AddCommentDTO;
import com.telran.ilcarro.model.comment.FullCommentDTO;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.CommentEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserEntityRepository userEntityRepository;

    /**
     * TODO
     * 1.worth adding a annotation for Null @CheckForNull
     */
    @Override
    public List<FullCommentDTO> getLatestComments(Integer num) {
        try {
            List<CommentEntity> comments = userEntityRepository.findAll().stream()
                    .flatMap(userEntity -> {
                        List<CommentEntity> commentsEntities = userEntity.getComments();
                        if (commentsEntities != null) {
                            return userEntity.getComments().stream();
                        }
                        return Stream.empty();
                    })
                    .sorted(Comparator.comparing(CommentEntity::getPostDateTime).reversed())
                    .limit(num)
                    .collect(Collectors.toList());

            return comments.stream().map(comment -> CommentMapper.INSTANCE.map(comment))
                    .collect(Collectors.toList());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    /**
     * TODO
     * 1. worth adding a annotation for Null @CheckForNull
     */
    @Override
    @CheckForNull
    public boolean postComment(String serialNumber, String ownerEmail, AddCommentDTO comment) {
        try {
            //TODO need check
            UserEntity userEntity = userEntityRepository.getUserEntityByOwnCarsContains(serialNumber)
                    .orElseThrow(() -> new NotFoundServiceException(String.format("Car id %s not found", serialNumber)));

            UserEntity postOwner = userEntityRepository.findById(ownerEmail)
                    .orElseThrow(() -> new NotFoundServiceException(String.format("Owner %s not found", ownerEmail)));
            List<CommentEntity> comm = userEntity.getComments();
            comm.add(CommentMapper.INSTANCE.map(comment, serialNumber, postOwner));
            userEntity.setComments(comm);
            userEntityRepository.save(userEntity);
            return true;
        } catch (Exception ex) {
            throw new ConflictServiceException(ex.getMessage());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }
}
