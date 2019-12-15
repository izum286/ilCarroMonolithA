package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.FeedbackDTO;
import com.telran.ilcarro.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedbackControllerImpl implements FeedbackController{

    @Autowired
    FeedbackService feedbackService;

    @GetMapping("feedback/{id}")
    @Override
    public FeedbackDTO getFeedbackById(@PathVariable("id") String id) {
        return feedbackService.getFeedbackById(id).orElseThrow();
    }

    @GetMapping("feedback/{owner}")
    @Override
    public List<FeedbackDTO> getFeedbacksByOwner(@PathVariable("owner") String owner) {
        return feedbackService.getFeedbacksByOwner(owner);
    }

    @GetMapping("feedback/popular/{num}")
    @Override
    public List<FeedbackDTO> getPopularFeedbacks(@PathVariable("num") Integer num) {
        return feedbackService.getPopularFeedbacks(num);
    }

    @GetMapping("feedback/last/{num}")
    @Override
    public List<FeedbackDTO> getLastFeedbacks(@PathVariable("num") Integer num) {
        return feedbackService.getLastFeedbacks(num);
    }

    @PostMapping("feedback")
    @Override
    public FeedbackDTO createFeedback(@RequestBody FeedbackDTO feedback) {
        return feedbackService.createFeedback(feedback).orElseThrow();
    }

    @PutMapping("feedback")
    @Override
    public FeedbackDTO updateFeedback(@RequestBody FeedbackDTO feedback) {
        return feedbackService.updateFeedback(feedback).orElseThrow();
    }

    @DeleteMapping("feedback/{id}")
    @Override
    public void deleteFeedback(@PathVariable("id") String id) {
        feedbackService.deleteFeedback(id);
    }
}
