package com.telran.ilcarro.model.web.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@ApiModel(value = "AddCommentDTO",description = "Comment data transfer object")
public class AddCommentDTO {
    @ApiModelProperty(notes = "Post text")
    private String post;
}
