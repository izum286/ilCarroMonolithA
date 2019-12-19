package com.telran.ilcarro.model.web.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telran.ilcarro.model.web.feedback.FeedbackDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUserDTO {
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime registrationDate;
    private FeedbackDTO comments;
    //TODO
    private String ownCars;
    //TODO
    private String bookedCars;
    //TODO
    private String history;
}
