package com.telran.ilcarro.service.mapper;
/**
 * @author Aleks Gor
 * car mapper for update & get car
 */

import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CommentMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    FullCarEntity map(AddUpdateCarDtoRequest dto);

    FullCarDTOResponse map(FullCarEntity entity);

    /**
     * used for ownerGetCarById - not providing owner in response
     * @param entity
     * @return
     */
    @Named("mapWithoutOwnerFullBookedPeriods")
    @Mapping(target = "owner", ignore = true)
    FullCarDTOResponse mapWithoutOwnerFullBookedPeriods(FullCarEntity entity);

}
