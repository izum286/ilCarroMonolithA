package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.FeedbackDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Override
    public Optional<FeedbackDTO> getFeedbackById(String id) {
        return Optional.empty();
    }

    @Override
    public List<FeedbackDTO> getFeedbacksByOwner(String owner) {
        return null;
    }

    @Override
    public List<FeedbackDTO> getPopularFeedbacks() {
        return null;
    }

    @Override
    public Optional<FeedbackDTO> createFeedback(FeedbackDTO feedback) {
        return Optional.empty();
    }

    @Override
    public Optional<FeedbackDTO> updateFeedback(FeedbackDTO feedback) {
        return Optional.empty();
    }

    @Override
    public boolean deleteFeedback(String id) {
        return false;
    }
}
