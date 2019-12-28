package com.telran.ilcarro.model.web;

import com.telran.ilcarro.model.web.user.PersonWhoBookedDto;
import lombok.*;

/**
 * @author vitalii_adler
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class Make_A_Reservation_DataParamsDto {
    private String start_date_time;
    private String end_date_time;
    PersonWhoBookedDto personWhoBookedDto;
}
