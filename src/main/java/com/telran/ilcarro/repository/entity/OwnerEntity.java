package com.telran.ilcarro.repository.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OwnerEntity {

    private String firstName;
    private String lastName;
    private String registrationDate;
}
