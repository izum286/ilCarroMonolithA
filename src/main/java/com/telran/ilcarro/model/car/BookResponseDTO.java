package com.telran.ilcarro.model.car;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "Reservation success response",description = "Reservation success response")
public class BookResponseDTO {
    private String order_number;
    private float amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String booking_date;
}