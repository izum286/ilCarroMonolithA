package com.telran.ilcarro.model.car.probably_unused;


import com.telran.ilcarro.model.car.PricePerDayDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Deprecated
public class ShortCarDTO {
    //TODO - probably unused class
    private double id;
    private String model;
    private String manufacture;
    private int year;
    private PricePerDayDto price_per_day;
    private List<String> image;
    private SpecsDTO specifications;
    private LocationDTO location;
    private boolean isRented;
}
