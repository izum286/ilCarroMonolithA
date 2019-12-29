package com.telran.ilcarro.model.car;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vitalii_adler
 * @author izum286
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@ApiModel(value = "AddUpdateCarDtoRequest",description = "Request from user to add or update existing car")
public class AddUpdateCarDtoRequest {
    private String serialNumber;
    private String make;
    private String model;
    private String year;
    private String engine;
    private String fuel;
    private String gear;
    private String wheelsDrive;
    private int doors;
    private int seats;
    private float fuel_consumption;
    List<FeatureDto> features;
    private String carClass;
    private float pricePerDay;
    private float distanceIncluded;
    private String about;
    PickUpPlaceDto pickUpPlaceDto;
    List<ImageUrlDto> imageUrlDto ;
}

