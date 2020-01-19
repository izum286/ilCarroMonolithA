package com.telran.ilcarro.service.mapper;
/**
 * @author Aleks Gor
 * for add new car only because we add new arrayList of Booking dto, and CarStat
 * for update etc @see CarMapper
 * 9.1.2020
 */

import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import com.telran.ilcarro.repository.entity.CarStatEntity;
import com.telran.ilcarro.repository.entity.CommentEntity;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;


@Mapper(componentModel = "spring",imports = {BookedPeriodEntity.class,
        ArrayList.class,
        CarStatEntity.class,
        CommentEntity.class,
        PickUpPlaceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface CarMapperAddCar {

    CarMapperAddCar INSTANCE = Mappers.getMapper(CarMapperAddCar.class);
    @Mapping(target = "bookedPeriods",expression = "java(new ArrayList<BookedPeriodEntity>())")
    @Mapping(target = "statistics",expression = "java(new CarStatEntity())")
    @Mapping(target = "pricePerDay.value", source = "pricePerDay")
    @Mapping(target = "pricePerDay.currency", constant = "ILS")
    FullCarEntity map(AddUpdateCarDtoRequest dto);

    @Mapping(target = "bookedPeriodDto",source = "bookedPeriods")
    @Mapping(target = "statistics",source = "statistics")
    FullCarDTOResponse map(FullCarEntity entity);

}
