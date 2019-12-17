package com.telran.ilcarro.repository.entity;

import lombok.*;
/**
 * @author vitalii_adler
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecsEntity {
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
