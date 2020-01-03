package com.telran.ilcarro.repository.entity;

import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.model.car.CarStatDto;
import com.telran.ilcarro.model.car.FeatureDto;
import com.telran.ilcarro.model.car.PickUpPlaceDto;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import lombok.*;


//TODO Заглушка FullCarEntity для CarService. Это надо переделать.
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FullCarEntity {
    private String serialNumber;
    private String model;
    private String manufacture;
    private int year;
    private String city;
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
    private PricePerDayEntity pricePerDay;
    private String pricePerDaySimple;
    private int distanceIncluded;
    private String about;
    private PickUpPlaceDto pickUpPlace;
    private List<String> images;
    //todo need to think about this
    private OwnerDtoResponse owner;
    private List<BookedPeriodDto> bookedPeriods;
    private CarStatDto statistics;
    //протоколе написано own_cars - эт список чего?
    private List<UserEntity> owner_cars;

    //возможно стоит создать объект Прайс, который будет иметь два поля(валюта и цена)?

    private List<String> image;



    private List<FeedbackEntity> feedBacks;

    private int trips;
    private List<SchedularUsageEntity> usages;
}
