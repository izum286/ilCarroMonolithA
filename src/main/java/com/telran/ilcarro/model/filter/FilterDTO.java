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
    private Integer year;
    private String engine;
    private String fuel;
    private String transmission;
    private String wheels_drive;
    private Double horsepower;
    private Double torque;
    private Integer doors;
    private Integer seats;
    private String car_class;
    private String fuel_consumption;
}
