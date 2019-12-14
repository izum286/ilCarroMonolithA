package com.telran.ilcarro.model.web;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {
    private UUID id;
    private long driverLicense;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String phone;
    private String email;
    private List<SchedularUsageDTO> usages;

    //TODO maybe transfer to separate class e.g. AccountDTO
    private String username;
    private String password;
    private String[] roles;

    public void setUsages(SchedularUsageDTO record ) {
        this.usages.add(record);
    }
}
