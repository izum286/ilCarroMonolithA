package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.car.FullCarDTOResponse;

/**
 * probably unused
 * @izum286
 */
public class CarDtoRequestToResponse {

    public static FullCarDTOResponse map(AddUpdateCarDtoRequest from) {
        return FullCarDTOResponse.builder()
                .serialNumber(from.getSerialNumber())
                .make(from.getMake())
                .model(from.getModel())
                .year(from.getYear())
                .engine(from.getEngine())
                .fuel(from.getFuel())
                .gear(from.getGear())
                .wheelsDrive(from.getWheelsDrive())
                .horsePower(from.getHorsePower())
                .torque(from.getTorque())
                .doors(from.getDoors())
                .seats(from.getSeats())
                .fuelConsumption(from.getFuelConsumption())
                .features(from.getFeatures())
                .carClass(from.getCarClass())
         //       .simplePricePerDay(from.getPricePerDay())
                .distanceIncluded(from.getDistanceIncluded())
                .about(from.getAbout())
                .pickUpPlace(from.getPickUpPlaceDto())
                .imageUrl(from.getImageUrl())
                //todo get owner by car id
                //.ownerDtoResponse(from.get)
                //todo get booked periods by car id
                //.bookedPeriodDto()
                //todo get carstat by car id

                .build();
    }
}
