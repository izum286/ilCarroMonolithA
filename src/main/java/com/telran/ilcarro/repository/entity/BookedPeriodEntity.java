package com.telran.ilcarro.repository.entity;

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
    private String carId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean paid;
    private boolean active;
    private float amount;
    private LocalDateTime bookingDate;
    PersonWhoBooked personWhoBooked;
}
