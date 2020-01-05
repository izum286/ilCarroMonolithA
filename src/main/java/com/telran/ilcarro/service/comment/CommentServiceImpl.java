package com.telran.ilcarro.service.comment;

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

    @Override
    public boolean postComment(String serialNumber, String ownerEmail, AddCommentDTO comment) {
        try {
            //TODO chek if serialNumber is exists from carRepo/ Now only for user test01
            String ownerOfCar = "test04";
            UserEntity userEntity = userEntityRepository.findById(ownerOfCar)
                    .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", ownerOfCar)));
            if (userEntity.getComments() == null) {
                userEntity.setComments(new ArrayList<>());
            }
            UserEntity postOwner = userEntityRepository.findById(ownerEmail)
                    .orElseThrow(() -> new NotFoundServiceException(String.format("Owner %s not found", ownerEmail)));
            userEntity.getComments().add(CommentMapper.INSTANCE.map(comment, serialNumber, postOwner));
            userEntityRepository.save(userEntity);
            return true;
        } catch (ConflictRepositoryException ex) {
            throw new ConflictServiceException(ex.getMessage());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }
}
