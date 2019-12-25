package com.telran.ilcarro.model.web;

import com.telran.ilcarro.model.web.user.OwnerDtoResponse;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FullCarDTOResponse {
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
    ArrayList<FeatureDto> featuresDto;
    private String car_class;
    private PricePerDayDto price_per_day;
    private float distance_included;
    private String about;
    PickUpPlaceDto pickUpPlaceDto;
    ArrayList<ImageUrlDto> imageUrlDto;
    OwnerDtoResponse ownerDtoResponse;
    ArrayList<BookedPeriodsDto> bookedPeriodsDto;
    StatisticsDto statisticsDto;
}
