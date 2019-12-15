package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.FeedbackDTO;

import java.util.List;

public interface FeedbackController {

    FeedbackDTO getFeedbackById(String id);

    List<FeedbackDTO> getFeedbacksByOwner(String owner);

    List<FeedbackDTO> getPopularFeedbacks(Integer num);

    List<FeedbackDTO> getLastFeedbacks(Integer num);

    FeedbackDTO createFeedback(FeedbackDTO feedback);

    FeedbackDTO updateFeedback(FeedbackDTO feedback);

    void deleteFeedback(String id);







}
