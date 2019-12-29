package com.telran.ilcarro.model.car;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.telran.ilcarro.model.user.PersonWhoBookedDto;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookedPeriodsDto {
    private String orderId;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private String startDateTime;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private String endDateTime;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean paid;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private float amount;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private String bookingDate;
    PersonWhoBookedDto personWhoBookedDto;

    public BookedPeriodsDto(String startDateTime, String endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public BookedPeriodsDto(String startDateTime, String endDateTime, PersonWhoBookedDto personWhoBookedDto) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.personWhoBookedDto = personWhoBookedDto;
    }

    public BookedPeriodsDto(String orderId, float amount, String bookingDate) {
        this.orderId = orderId;
        this.amount = amount;
        this.bookingDate = bookingDate;
    }
}
