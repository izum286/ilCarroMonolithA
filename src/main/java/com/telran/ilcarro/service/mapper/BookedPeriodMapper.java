package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.BookRequestDTO;
import com.telran.ilcarro.model.car.BookResponseDTO;
import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.model.user.PersonWhoBookedDto;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import com.telran.ilcarro.repository.entity.PersonWhoBooked;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * AleksGor
 * AntonKonkin
 * izum286
 */
@Mapper(uses = CommentMapper.class, imports = {BookedPeriodEntity.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookedPeriodMapper {

    BookedPeriodMapper INSTANCE = Mappers.getMapper(BookedPeriodMapper.class);

    BookedPeriodEntity map(BookedPeriodDto dto);

    @Mapping(target = "orderId", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "startDateTime", source = "start_date_time")
    @Mapping(target = "endDateTime", source = "end_date_time")
    @Mapping(target = "paid", expression = "java(false)")
    @Mapping(target = "active", expression = "java(true)")
    @Mapping(target = "bookingDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "personWhoBooked", source = "person_who_booked")
    BookedPeriodEntity map(BookRequestDTO dto);

    @Mapping(target = "order_number", source = "orderId")
    @Mapping(target = "booking_date", source = "bookingDate")
    BookResponseDTO mapToResponse(BookedPeriodEntity entity);




    BookedPeriodDto map(BookedPeriodEntity entity);

//    @Mapping(target = "personWhoBooked", ignore = true)
//    BookedPeriodDto mapForUserHistory(BookedPeriodEntity entity);

    /**
     * used for Get car by id for users - providing only start & and dates
     */
    @Mapping(target = "personWhoBookedDto", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "bookingDate", ignore = true)
    BookedPeriodDto mapForGetCarByIdForUsers(BookedPeriodEntity entity);


    PersonWhoBooked map(PersonWhoBookedDto dto);

    PersonWhoBookedDto map(PersonWhoBooked entity);
}
