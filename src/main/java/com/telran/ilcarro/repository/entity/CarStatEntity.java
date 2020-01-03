package com.telran.ilcarro.repository.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CarStatEntity {
    private int trips;
    private double rating;
}
