package com.telran.ilcarro.model.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

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
    @JsonProperty("serial_number")
    private String serialNumber;
    private String make;
    private String model;
    private String year;
    private String engine;
    private String fuel;
    private String gear;
    @JsonProperty("wheels_drive")
    private String wheelsDrive;
    @JsonProperty("horse_power")
    private float horsePower;
    private float torque;
    private int doors;
    private int seats;
    @JsonProperty("fuel_consumption")
    private float fuelConsumption;
    private List<FeatureDto> features;
    @JsonProperty("car_class")
    private String carClass;
    @JsonProperty("price_per_day")
    private PricePerDayDto pricePerDay;
    @JsonProperty("distance_included")
    private int distanceIncluded;
    private String about;
    @JsonProperty("pick_up_place")
    private PickUpPlaceDto pickUpPlaceDto;
    @JsonProperty("image_url")
    private List<String> imageUrl;
}

