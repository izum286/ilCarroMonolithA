package com.telran.ilcarro.repository.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PersonWhoBooked {
    private String email;
    private String first_name;
    private String second_name;
    private String phone;
}
