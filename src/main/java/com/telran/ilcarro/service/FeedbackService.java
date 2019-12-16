package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.feedback.AddFeedbackDto;
import com.telran.ilcarro.model.web.feedback.FeedbackDTO;
import com.telran.ilcarro.model.web.feedback.UpdFeedbackDTO;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    Optional<FeedbackDTO> getFeedbackById(String id);

    List<FeedbackDTO> getFeedbacksByOwner(String owner);

    List<FeedbackDTO> getPopularFeedbacks(Integer num);

    List<FeedbackDTO> getLastFeedbacks(Integer num);

    Optional<FeedbackDTO> createFeedback(AddFeedbackDto feedback);

    Optional<FeedbackDTO> updateFeedback(UpdFeedbackDTO feedback);

    boolean deleteFeedback(String id);


}
