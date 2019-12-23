package com.telran.ilcarro.model.web.feedback;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

//@ApiModel(value = "UpdFeedbackDTO",description = "Feedback data transfer object")

public class UpdFeedbackDTO {
    @ApiModelProperty(notes = "Id of feedback")
    private String id;
    @ApiModelProperty(notes = "Message of feedback")
    private String message;
    @ApiModelProperty(notes = "Owner of feedback")
    private String owner;
}
