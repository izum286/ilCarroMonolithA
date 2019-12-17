package com.telran.ilcarro.model.web;

import lombok.*;

/**
 * FilterDTO - we need to build our nodes of Filters tree
 * using data in this class
 * way:
 * FullCarDto -> FilterDto -> NodeTree
 * @author izum286
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilterDTO {
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
