package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * AleksGor
 * AntonKonkin
 * izum286
 */
@Mapper(uses = CommentMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookedPeriodMapper {

    BookedPeriodMapper INSTANCE = Mappers.getMapper(BookedPeriodMapper.class);


    BookedPeriodEntity map(BookedPeriodDto dto);

    BookedPeriodDto map(BookedPeriodEntity entity);

    @Mapping(target = "personWhoBookedDto", ignore = true)
    BookedPeriodDto mapForUserHistory(BookedPeriodEntity entity);

    /**
     * used for Get car by id for users - providing only start & and dates
     */
    @Mapping(target = "personWhoBookedDto", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "bookingDate", ignore = true)
    BookedPeriodDto mapForGetCarByIdForUsers(BookedPeriodEntity entity);
}
