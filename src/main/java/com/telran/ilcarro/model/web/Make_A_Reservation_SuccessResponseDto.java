package com.telran.ilcarro.model.web;

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
public class Make_A_Reservation_SuccessResponseDto {
    private String order_number;
    private float amount;
    private String booking_date;
}
