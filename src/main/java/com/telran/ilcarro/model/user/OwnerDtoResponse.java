package com.telran.ilcarro.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
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

public class OwnerDtoResponse {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("second_name")
    private String lastName;
    @JsonProperty("registration_date")
    private String registrationDate;
}
