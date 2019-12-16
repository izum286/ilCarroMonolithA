package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.feedback.FeedbackDTO;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    Optional<FeedbackDTO> getFeedbackById(String id);

    List<FeedbackDTO> getFeedbacksByOwner(String owner);

    List<FeedbackDTO> getPopularFeedbacks(Integer num);

    List<FeedbackDTO> getLastFeedbacks(Integer num);

    Optional<FeedbackDTO> createFeedback(FeedbackDTO feedback);

    Optional<FeedbackDTO> updateFeedback(FeedbackDTO feedback);

    boolean deleteFeedback(String id);


}
