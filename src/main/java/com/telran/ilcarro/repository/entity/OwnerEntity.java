package com.telran.ilcarro.repository.entity;

import lombok.*;
/**
 * @author Gor Aleks
 * 03.01.2020
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OwnerEntity {
    private String email;
    private String firstName;
    private String lastName;
    private String registrationDate;
}
