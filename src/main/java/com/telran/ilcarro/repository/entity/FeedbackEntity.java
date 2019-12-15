package com.telran.ilcarro.repository.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class FeedbackEntity {
    private String id;
    private String message;
    private String owner;
    private LocalDateTime date;
}
