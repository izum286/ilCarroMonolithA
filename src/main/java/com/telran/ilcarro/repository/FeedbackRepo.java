package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.FeedbackEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.repository.exception.RepositoryException;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class FeedbackRepo implements FeedbackRepository{

    Map<String, FeedbackEntity> feedbackEntities = new ConcurrentHashMap<>();


    @Override
    public FeedbackEntity getFeedbackById(String id) {
        FeedbackEntity curr = feedbackEntities.get(id);
        if(curr != null) {
            return curr;
        }
        throw new NotFoundRepositoryException(String.format("Feedback %s not found", id));
    }

    @Override
    public List<FeedbackEntity> getFeedbacksByOwner(String owner) {
        List<FeedbackEntity> feedbackList = feedbackEntities.values().stream()
                .filter(fb -> fb.getOwner().equals(owner))
                .collect(Collectors.toList());
        if (!feedbackList.isEmpty()) {
            return feedbackList;
        }
        throw new NotFoundRepositoryException(String.format("Feedbacks of %s not found", owner));
    }

    @Override
    public List<FeedbackEntity> getPopularFeedbacks(Integer num) {
        return getLastFeedbacks(num);
    }

    @Override
    public List<FeedbackEntity> getLastFeedbacks(Integer num) {
        if (num < 0) {
            throw new RepositoryException(String.format("Illegal argument %d must be bigger then 0", num));
        }
        List<FeedbackEntity> feedbackList = feedbackEntities.values().stream()
                .sorted(Comparator.comparing(FeedbackEntity::getDate).reversed())
                .collect(Collectors.toList());
        if (feedbackList.isEmpty()) {
            throw new NotFoundRepositoryException("Feedback's not found");
        }
        if (feedbackList.size() <= num) {
            return feedbackList;
        }
        return feedbackList.stream().limit(num).collect(Collectors.toList());
    }

    @Override
    public FeedbackEntity createFeedback(FeedbackEntity feedback) {
        if (feedbackEntities.putIfAbsent(feedback.getId(), feedback) == null) {
            return feedback;
        }
        throw new ConflictRepositoryException(String.format("Feedback with ID %s already exist", feedback.getId()));
    }

    @Override
    public FeedbackEntity updateFeedback(FeedbackEntity feedback) {
        if (feedbackEntities.replace(feedback.getId(), feedback) != null) {
            return feedback;
        };
        throw new NotFoundRepositoryException(String.format("Feedback with id: %s not found", feedback.getId()));
    }

    @Override
    public boolean deleteFeedback(String id) {
        if (feedbackEntities.remove(id) != null) {
            return true;
        }
        throw new NotFoundRepositoryException(String.format("Feedback with id: %s not found", id));
    }
}
