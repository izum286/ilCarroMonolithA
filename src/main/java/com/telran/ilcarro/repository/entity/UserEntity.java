package com.telran.ilcarro.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
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
    private boolean isDeleted;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime registrationDate;
    private UserDetailsEntity userDetails;
    private String photo;

    private List<SchedularUsageEntity> usages;

}

