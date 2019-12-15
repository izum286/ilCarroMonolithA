package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.FeedbackDTO;

import java.util.List;

/**
 * Feedback controller interface
 * Main methods to get dials with feedback.
 *
 *
 * @author Konkin Anton
 * @since 1.0
 *
 */

public interface FeedbackController {

    /**
     * Returns the feedback for selected id.
     * @param id of feedback
     * @return FeedbackDTO
     */
    FeedbackDTO getFeedbackById(String id);

    /**
     * Returns the list of feedback for selected owner.
     * @param owner of feedback
     * @return list of feedbackDTOs
     */
    List<FeedbackDTO> getFeedbacksByOwner(String owner);

    /**
     * Returns num of popular feedback's.
     * @param num of feedback's
     * @return list of feedbackDTOs
     */
    List<FeedbackDTO> getPopularFeedbacks(Integer num);

    /**
     * Returns num of last feedback's.
     * @param num of feedback's
     * @return list of feedbackDTOs
     */
    List<FeedbackDTO> getLastFeedbacks(Integer num);

    /**
     * (@code createFeedback) returns created feedback with full information.
     * @param feedback - new feedback
     * @return created feedback feedbackDTOs
     */
    FeedbackDTO createFeedback(FeedbackDTO feedback);

    /**
     * (@code updateFeedback) update information inside selected feedback.
     * @param feedback - new feedback
     * @return updated feedback feedbackDTOs
     */
    FeedbackDTO updateFeedback(FeedbackDTO feedback);
    /**
     * (@code deleteFeedback) delete selected feedback by id.
     * @param id - feedback id
     */
    void deleteFeedback(String id);







}
