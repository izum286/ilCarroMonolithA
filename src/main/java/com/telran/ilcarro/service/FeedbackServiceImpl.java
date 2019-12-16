package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.feedback.AddFeedbackDto;
import com.telran.ilcarro.model.web.feedback.FeedbackDTO;
import com.telran.ilcarro.model.web.feedback.UpdFeedbackDTO;
import com.telran.ilcarro.repository.FeedbackRepo;
import com.telran.ilcarro.repository.entity.FeedbackEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.repository.exception.RepositoryException;
import com.telran.ilcarro.service.converters.FeedbackDtoEntityConverter;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.telran.ilcarro.service.converters.FeedbackDtoEntityConverter.map;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackRepo feedbackRepo;

    @Override
    public Optional<FeedbackDTO> getFeedbackById(String id) {
        try {
            FeedbackEntity feedbackEntity = feedbackRepo.getFeedbackById(id);
            return Optional.of(map(feedbackEntity));
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public List<FeedbackDTO> getFeedbacksByOwner(String owner) {
        try {
            List<FeedbackEntity> feedbackList = feedbackRepo.getFeedbacksByOwner(owner);

            return feedbackList.stream()
                    .map(FeedbackDtoEntityConverter::map)
                    .collect(Collectors.toList());
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public List<FeedbackDTO> getPopularFeedbacks(Integer num) {
        try {
            List<FeedbackEntity> feedbackList = feedbackRepo.getPopularFeedbacks(num);

            return feedbackList.stream()
                    .map(FeedbackDtoEntityConverter::map)
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public List<FeedbackDTO> getLastFeedbacks(Integer num) {
        try {
            List<FeedbackEntity> feedbackList = feedbackRepo.getLastFeedbacks(num);

            return feedbackList.stream()
                    .map(FeedbackDtoEntityConverter::map)
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Optional<FeedbackDTO> createFeedback(AddFeedbackDto feedback) {
        try {
            FeedbackEntity feedbackEntity = feedbackRepo.createFeedback(map(feedback));
            return Optional.of(map(feedbackEntity));
        } catch (ConflictRepositoryException ex) {
            throw new ConflictServiceException(ex.getMessage(), ex.getCause());
        }

    }

    @Override
    public Optional<FeedbackDTO> updateFeedback(UpdFeedbackDTO feedback) {
        try {
            FeedbackEntity feedbackEntity = feedbackRepo.getFeedbackById(feedback.getId());
            feedbackEntity.setDate(LocalDateTime.now());
            feedbackEntity.setMessage(feedback.getMessage());
            feedbackEntity.setOwner(feedback.getOwner());
            return Optional.of(map(feedbackEntity));
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public boolean deleteFeedback(String id) {
        try {
            feedbackRepo.deleteFeedback(id);
            return true;
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }
}
