package com.telran.ilcarro.model.web;

import com.telran.ilcarro.model.web.user.PersonWhoBookedDto;
import lombok.*;

/**
 * @author vitalii_adler
 * @author izum286
 * diffent constructors added for implement different server responses
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class BookedPeriodsDto {
    private String order_id;
    private String start_date_time;
    private String end_date_time;
    private boolean paid;
    private float amount;
    private String booking_date;
    PersonWhoBookedDto personWhoBookedDto;

    public BookedPeriodsDto(String start_date_time, String end_date_time) {
        this.start_date_time = start_date_time;
        this.end_date_time = end_date_time;
    }

    public BookedPeriodsDto(String start_date_time, String end_date_time, PersonWhoBookedDto personWhoBookedDto) {
        this.start_date_time = start_date_time;
        this.end_date_time = end_date_time;
        this.personWhoBookedDto = personWhoBookedDto;
    }

    public BookedPeriodsDto(String order_id, float amount, String booking_date) {
        this.order_id = order_id;
        this.amount = amount;
        this.booking_date = booking_date;
    }
}
