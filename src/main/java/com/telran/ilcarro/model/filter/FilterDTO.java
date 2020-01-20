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
    private String year;
    private String engine;
    private String fuel;
    private String gear;
    private String wheels_drive;
    private String horsepower;
    private String torque;
    private String doors;
    private String seats;
    private String car_class;
    private String fuel_consumption;
}
