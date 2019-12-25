package com.telran.ilcarro.model.web;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class PricePerDayDto {

    private String currency;
    private Double value;
}
