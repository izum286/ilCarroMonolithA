package com.telran.ilcarro.model.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LocationDTO {
    private String country;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String lat;
    private String lon;
    private boolean isVehicle;
}
