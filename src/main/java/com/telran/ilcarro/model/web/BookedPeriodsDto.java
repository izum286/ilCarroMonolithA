package com.telran.ilcarro.model.web;

import com.telran.ilcarro.model.web.user.PersonWhoBookedDto;
import lombok.*;

/**
 * @author vitalii_adler
 * @author izum286
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
}
