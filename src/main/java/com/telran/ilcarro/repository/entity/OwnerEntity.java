package com.telran.ilcarro.repository.entity;

import lombok.*;
/**
 * @author Gor Aleks
 * @date 03.01.2020
 */
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
