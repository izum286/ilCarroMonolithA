package com.telran.ilcarro.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Deprecated
/**
 * For feedback use class CommentEntity
 */
public class FeedbackEntity {
    private String id;
    private String message;
    private String owner;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime date;
}
