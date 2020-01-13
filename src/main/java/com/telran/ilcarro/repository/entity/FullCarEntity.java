package com.telran.ilcarro.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

//TODO Заглушка FullCarEntity для CarService. Это надо переделать.

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FullCarEntity {
    @Id
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
    private List<FeatureEntity> features;
    private String carClass;
    private PricePerDayEntity pricePerDay;
    private int distanceIncluded;
    private String about;
    private PickUpPlaceEntity pickUpPlace;
    private List<String> imageUrl;
    private boolean isDeleted; //TODO check logic
    private String pricePerDaySimple;


    private OwnerEntity owner;


    private List<BookedPeriodEntity> bookedPeriods;


    private CarStatEntity statistics;
    private int trips;



}
