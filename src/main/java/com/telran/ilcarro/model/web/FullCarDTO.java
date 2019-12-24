package com.telran.ilcarro.model.web;

import com.telran.ilcarro.repository.entity.FeedbackEntity;
import com.telran.ilcarro.repository.entity.LocationEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FullCarDTO {
    private double id;
    private String model;
    private String manufacture;
    private int year;
    private SpecsDTO specifications;
    private double price;
    private List<String> image;
    private LocationEntity location;
    private List<String> features;
    private String owner;
    private int trips;
    private List<FeedbackEntity> feedBacks;
    private List<SchedularUsageDTO> usages;
    private boolean isRented = false;
    private String about;
}
