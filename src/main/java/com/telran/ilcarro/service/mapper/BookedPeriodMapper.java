package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CommentMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookedPeriodMapper {

    BookedPeriodMapper INSTANCE = Mappers.getMapper(BookedPeriodMapper.class);


    BookedPeriodEntity map(BookedPeriodDto dto);

    BookedPeriodDto map(BookedPeriodEntity entity);

    @Mapping(target = "personWhoBookedDto", ignore = true)
    BookedPeriodDto mapForUserHistory(BookedPeriodEntity entity);
}
