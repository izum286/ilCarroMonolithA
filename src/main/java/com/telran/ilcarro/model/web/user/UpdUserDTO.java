package com.telran.ilcarro.model.web.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@ApiModel(value = "AuthDTO",description = "Authorization data transfer object")
public class UpdUserDTO {
    @ApiModelProperty(notes = "User email")
    private String firstName;
    @ApiModelProperty(notes = "User password")
    private String lastName;
    @ApiModelProperty(notes = "Photo url")
    private String photo;
}
