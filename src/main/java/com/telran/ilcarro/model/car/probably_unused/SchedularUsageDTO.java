package com.telran.ilcarro.model.car.probably_unused;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SchedularUsageDTO {
    //TODO probably unused
    private String id;
    private String userId;
//    private UUID userId;
    private UUID carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
