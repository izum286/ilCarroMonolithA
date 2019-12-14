package com.telran.ilcarro.model.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FeedbackDTO {
    private String id;
    private String ownerImg;
    private String message;
    private String owner;
    private String date;
}
