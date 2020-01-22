package com.telran.ilcarro.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@ApiModel(value = "UpdUserDTO",description = "Update User data transfer object")
public class UpdUserDTO {
    @ApiModelProperty(notes = "User email")
    @JsonProperty("first_name")
    private String firstName;
    @ApiModelProperty(notes = "User password")
    @JsonProperty("second_name")
    private String lastName;
    @ApiModelProperty(notes = "Photo url")
    private String photo;
}
