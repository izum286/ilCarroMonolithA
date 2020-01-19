package com.telran.ilcarro.service.mapper;
/**
 * @author Aleks Gor
 * car mapper for update & get car
 */

import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommentMapper.class, BookedPeriodMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "pricePerDay.value", source = "pricePerDay")
    @Mapping(target = "pricePerDay.currency", defaultValue = "ILS")
    FullCarEntity map(AddUpdateCarDtoRequest dto);

    FullCarDTOResponse map(FullCarEntity entity);

    /**
     * FullCarEntity -> FullCarDTOResponse
     * used for ownerGetCarById - not providing owner in response
     * @param entity
     * @return
     */
    @Named("mapWithoutOwnerFullBookedPeriods")
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "bookedPeriodDto", source = "bookedPeriods", qualifiedByName = "BookedPeriodGeneralMapper")
    FullCarDTOResponse mapWithoutOwnerFullBookedPeriods(FullCarEntity entity);

    void updCar(@MappingTarget FullCarEntity carToUpd, FullCarEntity carFromUpd);

}
