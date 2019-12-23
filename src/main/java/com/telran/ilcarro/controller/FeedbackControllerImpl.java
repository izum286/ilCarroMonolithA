package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.feedback.AddFeedbackDto;
import com.telran.ilcarro.model.web.feedback.FeedbackDTO;
import com.telran.ilcarro.model.web.feedback.UpdFeedbackDTO;
import com.telran.ilcarro.service.FeedbackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
//@RequestMapping("/feedback")
public class FeedbackControllerImpl implements FeedbackController{
    /**
     * Feedback controller implementation
     * @see FeedbackController
     *
     * @author Konkin Anton
     * @since 1.0
     */

    @Autowired
    FeedbackService feedbackService;

    @ApiOperation(value = "Get feedback by id", response = FeedbackDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Feedback {id} not found"),
    }
    )

    @GetMapping("{id}")
    @Override
    public FeedbackDTO getFeedbackById(@PathVariable("id") String id) {
        return feedbackService.getFeedbackById(id).orElseThrow();
    }

    @ApiOperation(value = "Get feedback's by owner", response = FeedbackDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Feedbacks of {owner} not found"),
    }
    )
    @GetMapping("/owner/{owner}")
    @Override
    public List<FeedbackDTO> getFeedbacksByOwner(@PathVariable("owner") String owner) {
        return feedbackService.getFeedbacksByOwner(owner);
    }

    @ApiOperation(value = "Get popular N {num} feedback's", response = FeedbackDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Server error messages"),
    }
    )
    @GetMapping("popular/{num}")
    @Override
    public List<FeedbackDTO> getPopularFeedbacks(@PathVariable("num") Integer num) {
        return feedbackService.getPopularFeedbacks(num);
    }

    @ApiOperation(value = "Get last N {num} feedback's", response = FeedbackDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Server error messages"),
    }
    )
    @GetMapping("last/{num}")
    @Override
    public List<FeedbackDTO> getLastFeedbacks(@PathVariable("num") Integer num) {
        return feedbackService.getLastFeedbacks(num);
    }

    @ApiOperation(value = "Create feedback", response = FeedbackDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 409, message = "Feedback with ID %s already exist"),
    }
    )
    @PostMapping
    @Override
    public FeedbackDTO createFeedback(@RequestBody AddFeedbackDto feedback) {
        return feedbackService.createFeedback(feedback).orElseThrow();
    }

    @ApiOperation(value = "Update feedback", response = FeedbackDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Feedback {id} not found"),
    }
    )
    @PutMapping
    @Override
    public FeedbackDTO updateFeedback(@RequestBody UpdFeedbackDTO feedback) {
        return feedbackService.updateFeedback(feedback).orElseThrow();
    }

    @ApiOperation(value = "Delete feedback by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Feedback {id} not found"),
    }
    )
    @DeleteMapping("{id}")
    @Override
    public void deleteFeedback(@PathVariable("id") String id) {
        feedbackService.deleteFeedback(id);
    }
}
