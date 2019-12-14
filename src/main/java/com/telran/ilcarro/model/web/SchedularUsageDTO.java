package com.telran.ilcarro.model.web;

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
    private UUID userId;
    private UUID carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
