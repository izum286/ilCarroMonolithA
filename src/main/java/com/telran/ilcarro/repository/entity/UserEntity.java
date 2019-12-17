package com.telran.ilcarro.repository.entity;

import lombok.*;
import java.util.List;

/**
 * @author vitalii_adler
 *
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private String uuid;
    private long driverLicense;
    private String email;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String phone;

    private List<SchedularUsageEntity> usages;
    private UserDetailsEntity userDetailsEntity;
}

