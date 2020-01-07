package com.telran.ilcarro.repository.entity;

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
    private LocalDateTime bookingDate;
    PersonWhoBookedDto personWhoBookedDto;
}
