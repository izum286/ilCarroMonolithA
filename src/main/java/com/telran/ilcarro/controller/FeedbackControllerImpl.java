package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.FeedbackDTO;
import com.telran.ilcarro.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackControllerImpl implements FeedbackController{

    @Autowired
    FeedbackService feedbackService;

    @Override
    public FeedbackDTO getFeedbackById(String id) {
        return feedbackService.getFeedbackById(id).orElseThrow();
    }

    @Override
    public List<FeedbackDTO> getFeedbacksByOwner(String owner) {
        return feedbackService.getFeedbacksByOwner(owner);
    }

    @Override
    public List<FeedbackDTO> getPopularFeedbacks() {
        return feedbackService.getPopularFeedbacks();
    }

    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedback) {
        return feedbackService.createFeedback(feedback).orElseThrow();
    }

    @Override
    public FeedbackDTO updateFeedback(FeedbackDTO feedback) {
        return feedbackService.updateFeedback(feedback).orElseThrow();
    }

    @Override
    public void deleteFeedback(String id) {
        feedbackService.deleteFeedback(id);
    }
}
