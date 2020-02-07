package com.telran.ilcarro.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

public class OwnerDtoResponse {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("second_name")
    private String lastName;
    @JsonProperty("registration_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime registrationDate;
}
