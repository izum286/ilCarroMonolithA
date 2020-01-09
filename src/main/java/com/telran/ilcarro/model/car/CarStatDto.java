package com.telran.ilcarro.model.car;

import lombok.*;
/**
 * @author izum286
 * embedded object for FullCarDtoResponse
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarStatDto {
    private int trips;
    private double rating;
}
