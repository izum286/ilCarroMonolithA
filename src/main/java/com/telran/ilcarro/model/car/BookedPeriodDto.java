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
    private String orderId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean paid;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private float amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String bookingDate;
    PersonWhoBookedDto personWhoBookedDto;
}
