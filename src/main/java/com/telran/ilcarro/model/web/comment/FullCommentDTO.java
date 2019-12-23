package com.telran.ilcarro.model.web.comment;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate post_date;
    private String post;
    private String photo;
}
