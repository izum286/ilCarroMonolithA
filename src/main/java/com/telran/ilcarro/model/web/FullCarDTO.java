package com.telran.ilcarro.model.web;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class FullCarDTO {
    private double id;
    private String model;
    private String manufacture;
    private int year;
    private double price;
    private List<String> image;
    private SpecsDTO specifications;
    private LocationDTO location;
    private String about;
    private String features;
    private String owner;
    private int trips;
    private List<FeedbackDTO> feedbacks;
    private List<SchedularUsageDTO> usages;
    private boolean isRented = false;

    public void setUsages(SchedularUsageDTO record ) {
        this.usages.add(record);
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
