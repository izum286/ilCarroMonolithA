package com.telran.ilcarro.repository.entity;

import lombok.*;

import java.util.List;
/**
 * @author vitalii_adler
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortCarEntity {
    private double id;
    private String model;
    private String manufacture;
    private int year;
    private double price;
    private List<String> image;
    private SpecsEntity specifications;
    private LocationEntity location;
    private boolean isRented;
}
