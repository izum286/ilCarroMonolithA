package com.telran.ilcarro.model.car;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telran.ilcarro.model.user.PersonWhoBookedDto;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "Make a reservation",description = "Dto request for book a car")
public class BookRequestDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start_date_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end_date_time;
    PersonWhoBookedDto person_who_booked;
}
