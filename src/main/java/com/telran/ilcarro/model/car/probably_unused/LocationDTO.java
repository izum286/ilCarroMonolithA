package com.telran.ilcarro.model.car.probably_unused;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Deprecated
public class LocationDTO {
    //TODO - probably unused class
    private String country;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String lat;
    private String lon;
    private boolean isVehicle;
}
