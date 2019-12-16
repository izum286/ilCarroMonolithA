package com.telran.ilcarro.model.web;

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

@ApiModel(value = "FeedbackDTO",description = "Feedback data transfer object")

public class FeedbackDTO {
    @ApiModelProperty(notes = "Id of feedback")
    private String id;
    @ApiModelProperty(notes = "Message of feedback")
    private String message;
    @ApiModelProperty(notes = "Owner of feedback")
    private String owner;
    @ApiModelProperty(notes = "Date when feedback left")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime date;
}
