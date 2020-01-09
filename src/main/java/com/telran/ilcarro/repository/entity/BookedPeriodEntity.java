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
@Document(collection = "bookedPeriods")
public class BookedPeriodEntity {
    @Id
    private String orderId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean paid;
    private boolean active;
    private float amount;
    private LocalDateTime bookingDate;
    PersonWhoBookedDto personWhoBookedDto;
}
