package com.telran.ilcarro.model.car;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.telran.ilcarro.model.user.PersonWhoBookedDto;
import lombok.*;

import java.time.LocalDateTime;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookedPeriodDto {
    private String order_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start_date_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end_date_time;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean paid;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private float amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String booking_date;
    PersonWhoBookedDto person_who_booked;
}
