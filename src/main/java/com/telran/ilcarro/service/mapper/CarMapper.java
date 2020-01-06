package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.repository.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CommentMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    FullCarEntity map(AddUpdateCarDtoRequest dto);

    @Mapping(target = "imageUrl", source = "entity.image")
    FullCarDTOResponse map(FullCarEntity entity);

    BookedPeriodDto bookedPeriodEntityToBookedPeriodDto(BookedPeriodEntity entity);
    BookedPeriodEntity bookedPeriodDtoToBookedPeriodEntity(BookedPeriodDto dto);

    FeatureDto featureEntityToFeatureDto(FeatureEntity entity);
    FeatureEntity featureDtoToFeatureEntity(FeatureDto dto);

    OwnerDtoResponse ownerEntityToOwnerDtoResponse(OwnerEntity entity);
    OwnerEntity ownerDtoResponseToOwnerEntity(OwnerDtoResponse dto);

    PickUpPlaceDto pickUpPlaceEntityToPickUpPlaceDto(PickUpPlaceEntity entity);
    PickUpPlaceEntity pickUpPlaceDtoToPickUpPlaceEntity(PickUpPlaceDto dto);

    PricePerDayDto pricePerDayEntityToPricePerDayDto(PricePerDayEntity entity);
    PricePerDayEntity pricePerDatDtoToPricePerDayEntity(PricePerDayDto dto);
}
