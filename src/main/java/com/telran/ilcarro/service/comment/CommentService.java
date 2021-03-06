package com.telran.ilcarro.service.comment;

import com.telran.ilcarro.model.comment.AddCommentDTO;
import com.telran.ilcarro.model.comment.FullCommentDTO;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;

import java.util.List;

/**
 * CommentService interface
 * @author Konkin Anton
 *
 */
public interface CommentService {
    /**
     * Method return (num) last posts
     * * @param num - number of last posts
     * @return List of Comments DTO
     */
    List<FullCommentDTO> getLatestComments(Integer num);

    /**
     * Method add new comment and  bind it with carId, carOwner and owner of comment
     * @param serialNumber - id of car
     * @param ownerEmail - owner of post
     * @param comment - message
     * @return true if Ok, or - throw exception
     * @throws ConflictServiceException - //TODO need to check and cleanup
     * @throws NotFoundServiceException - if user, or serial number not found in DB
     */

    boolean postComment(String serialNumber, String ownerEmail, AddCommentDTO comment) throws ConflictServiceException, NotFoundServiceException;
}
