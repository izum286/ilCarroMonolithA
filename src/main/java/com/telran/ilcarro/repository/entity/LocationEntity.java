package com.telran.ilcarro.repository.entity;

import lombok.*;
/**
 * @author vitalii_adler
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationEntity {
    private String country;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String lat;
    private String lon;
}
