package com.telran.ilcarro.model.web.feedback;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@ApiModel(value = "AddFeedbackDTO",description = "Feedback data transfer object")

public class AddFeedbackDto {

    @ApiModelProperty(notes = "Message of feedback")
    private String message;
    @ApiModelProperty(notes = "Owner of feedback")
    private String owner;
}
