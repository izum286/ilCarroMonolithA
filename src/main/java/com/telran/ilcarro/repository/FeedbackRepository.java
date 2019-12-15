package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.web.FeedbackDTO;
import com.telran.ilcarro.repository.entity.FeedbackEntity;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository {

    FeedbackEntity getFeedbackById(String id);

    List<FeedbackEntity> getFeedbacksByOwner(String owner);

    List<FeedbackEntity> getPopularFeedbacks(Integer num);

    List<FeedbackEntity> getLastFeedbacks(Integer num);

    FeedbackEntity createFeedback(FeedbackEntity feedback);

    FeedbackEntity updateFeedback(FeedbackEntity feedback);

    boolean deleteFeedback(String id);


}
