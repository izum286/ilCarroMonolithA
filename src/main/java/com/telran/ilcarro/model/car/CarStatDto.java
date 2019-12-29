package com.telran.ilcarro.model.car;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
/**
 * @author izum286
 */
public class CarStatDto {
    private int trips;
    private double rating;
}
