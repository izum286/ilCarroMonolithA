package com.telran.ilcarro.repository.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class PricePerDayEntity {

    private String currency;
    private float value;
}
