package com.telran.ilcarro.model.web.user;

import lombok.*;

/**
 * @author vitalii_adler
 * use for UserDetailsEntity
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthDTO {
    private String email;
    private String password;
}
