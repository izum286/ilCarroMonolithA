package com.telran.ilcarro.model.car;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SpecsDTO {
    private String engine;
    private String fuelCons;
    private String fuelType;
    private String transmission;
    private String wd;
    private double hp;
    private double torque;
    private int doors;
    private int seats;
    private String clasz;
}
