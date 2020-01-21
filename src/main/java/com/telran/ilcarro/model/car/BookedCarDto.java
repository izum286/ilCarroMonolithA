package com.telran.ilcarro.model.car;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
/*
 * Used inside FullUserDto for booked_cars and history
 */
public class BookedCarDto {
    private String serial_number;
    private BookedPeriodDto booked_period;
}
