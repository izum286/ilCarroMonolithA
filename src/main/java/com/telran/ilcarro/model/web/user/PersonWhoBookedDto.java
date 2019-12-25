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

public class PersonWhoBookedDto {
    private String email;
    private String first_name;
    private String second_name;
    private String phone;
}
