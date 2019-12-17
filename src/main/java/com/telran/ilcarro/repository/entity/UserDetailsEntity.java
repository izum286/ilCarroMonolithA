package com.telran.ilcarro.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author vitalii_adler
 * Use for auth
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDetailsEntity {
    private String email;
    private String password;
    private List<UserRoleEntity> roles;
    private String owner_email;
}
