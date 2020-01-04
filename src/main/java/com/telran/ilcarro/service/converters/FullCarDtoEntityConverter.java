package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.repository.entity.FullCarEntity;

/**
 *
 * FullCarDTOResponse <---> FullCarEntity
 * Implementation of convertation logic for CarService with FullCarDTOResponse and FullCarEntity
 * @author Gor Aleks
 * @date 03.01.2020
 */

public class FullCarDtoEntityConverter {

    public static FullCarDTOResponse map(FullCarEntity entity) {
        return FullCarDTOResponse.builder()
                .about(entity.getAbout())
                .carClass(entity.getCarClass())
                .distanceIncluded(entity.getDistanceIncluded())
                .doors(entity.getDoors())
                .engine(entity.getEngine())
                .features(entity.getFeatures())
                .fuel(entity.getFuel())
                .fuelConsumption(entity.getFuelConsumption())
                .gear(entity.getGear())
                .imageUrl(entity.getImage())
                .make(entity.getMake())
                .model(entity.getModel())
                .pickUpPlace(entity.getPickUpPlace())
                .seats(entity.getSeats())
                .serialNumber(entity.getSerialNumber())
                .pricePerDay(PricePerDayDtoEntityCoverter.map(entity.getPricePerDay()))
                .wheelsDrive(entity.getWheelsDrive())
                .year(entity.getYear())
                .bookedPeriodDto(entity.getBookedPeriods())
                .horsePower(entity.getHorsePower())
                .ownerDtoResponse(entity.getOwner())
                .statisticsDto(entity.getStatistics())
                .torque(entity.getTorque())

                //null. В процессе
                .simplePricePerDay(0)

                .build();
    }

    public static FullCarEntity map(FullCarDTOResponse dto) {
        return FullCarEntity.builder()
                .about(dto.getAbout())
                .bookedPeriods(dto.getBookedPeriodDto())
                .carClass(dto.getCarClass())
                .distanceIncluded(dto.getDistanceIncluded())
                .doors(dto.getDoors())
                .engine(dto.getEngine())
                .features(dto.getFeatures())

                //null отсутствует поле FeedBacks в FullCarDtoResponse. В процессе
                .feedBacks(null)

                .fuel(dto.getFuel())
                .fuelConsumption(dto.getFuelConsumption())
                .gear(dto.getGear())
                .horsePower(dto.getHorsePower())
                .image(dto.getImageUrl())

                .make(dto.getMake())
                .model(dto.getModel())
                .owner(dto.getOwnerDtoResponse())

                //null отсутсвует поле owner_cars в FullCatDtoResponse. В процессе
                .owner_cars(null)

                .pickUpPlace(dto.getPickUpPlace())
                .pricePerDay(PricePerDayDtoEntityCoverter.map(dto.getPricePerDay()))
                .seats(dto.getSeats())
                .serialNumber(dto.getSerialNumber())
                .statistics(dto.getStatisticsDto())
                .torque(dto.getTorque())

                //null отсутсвует поле trips в FullCatDtoResponse. В процессе
                .trips(0)

                //null отсутсвует поле usages в FullCatDtoResponse. В процессе
                .usages(null)

                .wheelsDrive(dto.getWheelsDrive())
                .year(dto.getYear())
                .build();
    }
}
