package com.telran.ilcarro.repository.entity;

import com.telran.ilcarro.model.user.PersonWhoBookedDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private boolean active;
    private float amount;
    private LocalDateTime bookingDate;
    PersonWhoBooked personWhoBooked;
}
