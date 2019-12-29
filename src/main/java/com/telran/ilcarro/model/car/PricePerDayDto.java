package com.telran.ilcarro.model.car;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class PricePerDayDto {
    private String currency;
    private float value;
}
