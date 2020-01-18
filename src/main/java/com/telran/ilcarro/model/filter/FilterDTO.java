package com.telran.ilcarro.model.filter;

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
    private String model;
    private int year;
    private String engine;
    private String fuel;
    private String transmission;
    private String wheels_drive;
    private double horsepower;
    private double torque;
    private int doors;
    private int seats;
    private String car_class;
    private String fuel_consumption;
}
