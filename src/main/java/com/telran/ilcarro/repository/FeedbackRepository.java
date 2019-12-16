package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.FeedbackEntity;

import java.util.List;

public interface FeedbackRepository {

    FeedbackEntity getFeedbackById(String id);

    List<FeedbackEntity> getFeedbacksByOwner(String owner);

    List<FeedbackEntity> getPopularFeedbacks(Integer num);

    List<FeedbackEntity> getLastFeedbacks(Integer num);

    FeedbackEntity createFeedback(FeedbackEntity feedback);

    FeedbackEntity updateFeedback(FeedbackEntity feedback);

    boolean deleteFeedback(String id);


}
