package com.telran.ilcarro.repository.entity;

import lombok.*;


//TODO Заглушка FullCarEntity для CarService. Это надо переделать.
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FullCarEntity {
    private double id;
    private String model;
    private String manufacture;
    private int year;
    //протоколе написано own_cars - эт список чего?
    private List<UserEntity> owner_cars;
    private List<String> features;
    //возможно стоит создать объект Прайс, который будет иметь два поля(валюта и цена)?
    private double pricePerDay;
    private List<String> image;
    private SpecsEntity specifications;
    private LocationEntity location;
    private boolean isRented;
    private String about;
    private List<FeedbackEntity> feedBacks;
    private String owner;
    private int trips;
    private List<SchedularUsageEntity> usages;
}
