package com.telran.ilcarro.model.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FeedbackDTO {
    private String id;
    private String message;
    private String owner;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime date;
}
