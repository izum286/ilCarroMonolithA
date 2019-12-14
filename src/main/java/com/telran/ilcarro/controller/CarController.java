package com.telran.ilcarro.controller;

import org.springframework.http.ResponseEntity;

public interface CarController {
    ResponseEntity getSingleCar(String carId);

    ResponseEntity getPopularCar();

    ResponseEntity getSingleFullCar(String carId);

    ResponseEntity findByLocationAndArea(String location, String area);

    ResponseEntity findByLocationAndDates(String location, String dateFrom, String dateTo);

    ResponseEntity findByLocationDatePrice(String location, String from, String to, String priceFrom, String priceTo);

    ResponseEntity addCar(String data);

    ResponseEntity rentCar(String data);

    ResponseEntity returnCar(String carId);

    ResponseEntity checkIfAvailable(String data);
}
