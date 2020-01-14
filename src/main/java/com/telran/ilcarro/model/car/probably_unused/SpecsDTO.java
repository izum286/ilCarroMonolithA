package com.telran.ilcarro.model.car.probably_unused;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Deprecated
public class SpecsDTO {
    //TODO - probably unused class
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
