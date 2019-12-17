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
public class FilterEntity {
    private String make;
    private String models;
    private String years;
    private String engines;
    private String fuel;
    private String transmissions;
    private String wd;
    private String horsepower;
    private String torque;
    private String doors;
    private String seats;
    private String classs;
    private String fuelConsumption;
}
