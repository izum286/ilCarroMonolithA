package com.telran.ilcarro.model.car;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * @izum286
 * in some requests we need field price per day as an Object
 * (Owner get car by Id && Owner get cars), in all others - only simple field
 * annotations
 *     @JsonInclude(JsonInclude.Include.NON_NULL)
 *     private PricePerDayDto pricePerDay;
 *     @JsonInclude(JsonInclude.Include.NON_DEFAULT)
 *     private float simplePricePerDay;
 * allow us ignore null or default fields during transfer to frontEnd,
 * and we dont need to write a lot of similar DTO's to provide this functionality.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@ApiModel(value = "FullCarDTOResponse",description = "Full Car data transfer object")
public class FullCarDTOResponse {
    private String serialNumber;
    private String model;
    private String make;
    private String year;
    private String engine;
    private String fuel;
    private String gear;
    private String wheelsDrive;
    private float horsePower;
    private float torque;
    private int doors;
    private int seats;
    private float fuelConsumption;
    private List<FeatureDto> features;
    private String carClass;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PricePerDayDto pricePerDay;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private float pricePerDaySimple;
    List<String> imageUrl;
    private int distanceIncluded;
    private String about;
    PickUpPlaceDto pickUpPlace;

    OwnerDtoResponse owner;

    List<BookedPeriodDto> bookedPeriodDto;
    CarStatDto statisticsDto;


}
