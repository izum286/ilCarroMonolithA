package com.telran.ilcarro.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegUserDTO {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("second_name")
    private String lastName;
}
