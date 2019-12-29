package com.telran.ilcarro.model.car;

import lombok.*;

/**
 * @author vitalii_adler
 * @author izum286
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class StatisticsDto {
    private String trips;
    private String rating;
}
