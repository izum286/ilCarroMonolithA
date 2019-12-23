package com.telran.ilcarro.model.web.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
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
    private String first_name;
    private String second_name;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime post_date;
    private String post;
    private String photo;
}
