package com.telran.ilcarro.model.comment;

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

@ApiModel(value = "FullCommentDTO", description = "FullComment data transfer object")
public class FullCommentDTO {
    @ApiModelProperty(notes = "Owner first name")
    private String first_name;
    @ApiModelProperty(notes = "Owner second name")
    private String second_name;
    @ApiModelProperty(notes = "Date when message was left")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime post_date;
    @ApiModelProperty(notes = "Message")
    private String post;
    @ApiModelProperty(notes = "Avatar of owner")
    private String photo;
}
