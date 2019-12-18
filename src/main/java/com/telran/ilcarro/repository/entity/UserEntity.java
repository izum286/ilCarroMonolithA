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
    private String email;
    private String driverLicense;
    private String firstName;
    private String lastName;
    private LocationEntity location;
    private String phone;

    private List<SchedularUsageEntity> usages;
    private UserDetailsEntity userDetails;
}

