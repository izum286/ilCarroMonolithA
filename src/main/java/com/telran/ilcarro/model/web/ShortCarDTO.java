package com.telran.ilcarro.model.web;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ShortCarDTO {
    private double id;
    private String model;
    private String manufacture;
    private int year;
    private double price;
    private List<String> image;
    private SpecsDTO specifications;
    private LocationDTO location;
    private boolean isRented;
}
