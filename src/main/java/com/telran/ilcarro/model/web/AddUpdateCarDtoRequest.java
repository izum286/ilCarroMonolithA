package com.telran.ilcarro.model.web;

import lombok.*;

import java.util.ArrayList;
/**
 * @author vitalii_adler
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class AddUpdateCarDtoRequest {
    private String serial_number;
    private String make;
    private String model;
    private String year;
    private String engine;
    private String fuel;
    private String gear;
    private String wheels_drive;
    private float doors;
    private float seats;
    private float fuel_consumption;
    ArrayList<FeatureDto> features;
    private String car_class;
    private PricePerDayDto price_per_day;
    private float distance_included;
    private String about;
    PickUpPlaceDto pickUpPlaceDto;
    ArrayList<ImageUrlDto> imageUrlDto ;
}

