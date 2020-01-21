package com.telran.ilcarro.model.car;

import com.fasterxml.jackson.annotation.*;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.repository.entity.PricePerDayEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * @author izum286
 * in some requests we need field price per day as an Object
 * (Owner get car by Id && Owner get cars), in all others - only simple field
 * annotations
 *     JsonInclude(JsonInclude.Include.NON_NULL)
 *     private PricePerDayDto pricePerDay;
 *     JsonInclude(JsonInclude.Include.NON_DEFAULT)
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
    @JsonProperty("serial_number")
    private String serialNumber;
    private String model;
    private String make;
    private String year;
    private String engine;
    private String fuel;
    private String gear;
    @JsonProperty("wheels_drive")
    private String wheelsDrive;
    @JsonProperty("horsepower")
    private float horsePower;
    private float torque;
    private int doors;
    private int seats;
    @JsonProperty("fuel_consumption")
    private float fuelConsumption;
    private List<FeatureDto> features;
    @JsonProperty("car_class")
    private String carClass;
    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private PricePerDayDto pricePerDay;
//    @JsonIgnore
    @JsonProperty("price_per_day")
    private Object pricePerDay;
//    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
//    private float pricePerDaySimple;
    @JsonProperty("image_url")
    List<String> imageUrl;
    @JsonProperty("distance_included")
    private int distanceIncluded;
    private String about;
    @JsonProperty("pick_up_place")
    PickUpPlaceDto pickUpPlace;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    OwnerDtoResponse owner;
    @JsonProperty("booked_periods")
    List<BookedPeriodDto> bookedPeriodDto;
    @JsonProperty("statistics")
    private CarStatDto statistics;

//    @JsonAnySetter
//    public void setpricePerDay(Object value) {
//        if (value instanceof Float) {
//            pricePerDay = (Float) value;
//        }
//        if (value instanceof PricePerDayEntity) {
//            pricePerDay = new PricePerDayDto(((PricePerDayEntity) value).getCurrency(), ((PricePerDayEntity) value).getValue());
//        }
//    }

}
