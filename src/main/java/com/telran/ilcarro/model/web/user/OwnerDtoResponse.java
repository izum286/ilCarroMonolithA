package com.telran.ilcarro.model.web.user;

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
    private String firstName;
    private String lastName;
    private String registrationDate;
}
