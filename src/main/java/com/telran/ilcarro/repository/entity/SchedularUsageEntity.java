package com.telran.ilcarro.repository.entity;

import lombok.*;

import java.time.LocalDate;
/**
 * @author vitalii_adler
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Deprecated
public class SchedularUsageEntity {
    private String id;
    private String userId;
    private String carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
