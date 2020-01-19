package com.telran.ilcarro.service.mapper;

import com.telran.ilcarro.model.car.BookRequestDTO;
import com.telran.ilcarro.model.car.BookResponseDTO;
import com.telran.ilcarro.model.car.BookedCarDto;
import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.model.user.PersonWhoBookedDto;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import com.telran.ilcarro.repository.entity.PersonWhoBooked;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
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


    /**
     * BookedPeriodEntity -> BookedPeriodDto
     * @param entity BookedPeriodEntity
     * @return BookedPeriodDto
     */
    @Named("BookedPeriodFullMapper")
    @Mapping(target = "order_id", source = "orderId")
    @Mapping(target = "start_date_time", source = "startDateTime")
    @Mapping(target = "end_date_time", source = "endDateTime")
    @Mapping(target = "booking_date", source = "bookingDate")
    @Mapping(target = "person_who_booked", source = "personWhoBooked")
    BookedPeriodDto map(BookedPeriodEntity entity);



//    @Mapping(target = "personWhoBooked", ignore = true)
//    BookedPeriodDto mapForUserHistory(BookedPeriodEntity entity);

    /**
     * used for Get car by id for users - providing only start & and dates
     */
    @Named("mapForGetCarByIdForUsers")
    @Mapping(target = "person_who_booked", ignore = true)
    @Mapping(target = "order_id", ignore = true)
    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "booking_date", ignore = true)
    BookedPeriodDto mapForGetCarByIdForUsers(BookedPeriodEntity entity);


    PersonWhoBooked map(PersonWhoBookedDto dto);

    PersonWhoBookedDto map(PersonWhoBooked entity);

    @Named("mapBookedPeriodToBookedCar")
    @Mapping(target = "booked_period.order_id", source = "orderId")
    @Mapping(target = "booked_period.start_date_time", source = "startDateTime")
    @Mapping(target = "booked_period.end_date_time", source = "endDateTime")
    @Mapping(target = "booked_period.booking_date", source = "bookingDate")
    @Mapping(target = "booked_period.person_who_booked", source = "personWhoBooked")
    @Mapping(target = "serial_number", source = "carId")
    BookedCarDto mapBookedPeriodToBookedCarDto(BookedPeriodEntity bookedPeriodEntity);
}
