package com.telran.ilcarro.model.car;

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
    private String id;
    private String userId;
//    private UUID userId;
    private UUID carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
