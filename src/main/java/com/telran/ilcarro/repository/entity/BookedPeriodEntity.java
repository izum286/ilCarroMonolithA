package com.telran.ilcarro.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.telran.ilcarro.model.user.PersonWhoBookedDto;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookedPeriodEntity {
    private String orderId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean paid;
    private float amount;
    private String bookingDate;
    PersonWhoBookedDto personWhoBookedDto;
}
