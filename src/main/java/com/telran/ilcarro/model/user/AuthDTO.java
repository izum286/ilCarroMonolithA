package com.telran.ilcarro.model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author vitalii_adler
 * @author Anton Konkin
 * use for UserDetailsEntity
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@ApiModel(value = "AuthDTO",description = "Authorization data transfer object")
public class AuthDTO {
    @ApiModelProperty(notes = "User email")
    private String email;
    @ApiModelProperty(notes = "User password")
    private String password;
}
