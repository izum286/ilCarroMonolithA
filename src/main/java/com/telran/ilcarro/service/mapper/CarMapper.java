package com.telran.ilcarro.service.mapper;
/**
 * @author Aleks Gor
 * car mapper for update & get car
 */

import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.car.CarStatDto;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.car.PickUpPlaceDto;
import com.telran.ilcarro.repository.entity.CarStatEntity;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.entity.PickUpPlaceEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommentMapper.class,
        BookedPeriodMapper.class,
        PricePerDayMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    @Mapping(target = "bookedPeriods", expression = "java(new ArrayList<BookedPeriodEntity>())")
    @Mapping(target = "statistics", expression = "java(new CarStatEntity(0, 0))")
    @Mapping(target = "pricePerDay.value", source = "pricePerDay")
    @Mapping(target = "pricePerDay.currency", constant = "ILS")
    @Mapping(target = "pickUpPlace", source = "pickUpPlaceDto")
    FullCarEntity map(AddUpdateCarDtoRequest dto);

//    @Mapping(target = "pricePerDay.currency", source = "pricePerDay.currency", defaultValue = "ILS")
    @Mapping(target = "pricePerDay", source = "pricePerDay")
    @Mapping(target = "statistics", source = "statistics", defaultExpression = "java(new CarStatDto(0, 0))")
    @Mapping(target = "bookedPeriodDto", source = "bookedPeriods", defaultExpression = "java(new ArrayList<BookedPeriodDto>())")
    @Mapping(target = "pickUpPlace", source = "pickUpPlace", defaultExpression = "java(new PickUpPlaceDto(\"none\", -1, -1))")
    FullCarDTOResponse map(FullCarEntity entity);

    @Mapping(target = "pricePerDay", source = "pricePerDay.value")
    @Mapping(target = "statistics", source = "statistics", defaultExpression = "java(new CarStatDto(0, 0))")
    @Mapping(target = "bookedPeriodDto", source = "bookedPeriods",
            qualifiedByName = "mapForGetCarByIdForUsers",
            defaultExpression = "java(new ArrayList<BookedPeriodDto>())")
    @Mapping(target = "pickUpPlace", source = "pickUpPlace", defaultExpression = "java(new PickUpPlaceDto(\"none\", -1, -1))")
    FullCarDTOResponse mapForGetCarByIdForUsers(FullCarEntity entity);

    /**
     * FullCarEntity -> FullCarDTOResponse
     * used for ownerGetCarById - not providing owner in response
     * @param entity
     * @return
     */
    @Named("mapWithoutOwnerFullBookedPeriods")
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "bookedPeriodDto", source = "bookedPeriods", qualifiedByName = "BookedPeriodFullMapper")
//    @Mapping(target = "pricePerDay.currency", source = "pricePerDay.currency", defaultValue = "ILS")
    @Mapping(target = "pricePerDay", source = "pricePerDay")
    FullCarDTOResponse mapWithoutOwnerFullBookedPeriods(FullCarEntity entity);

    void updCar(@MappingTarget FullCarEntity carToUpd, FullCarEntity carFromUpd);

    CarStatEntity map(CarStatDto statDto);

    CarStatDto map(CarStatEntity statEntity);

    PickUpPlaceEntity map(PickUpPlaceDto dto);

    PickUpPlaceDto map(PickUpPlaceEntity entity);

}
