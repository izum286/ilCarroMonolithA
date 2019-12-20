package com.telran.ilcarro.repository.entity;

import lombok.*;

import java.util.List;

/**
 * @author vitalii_adler
 * @author Konkin Anton
 * Use for auth
 * @since 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDetailsEntity {
    private String email;
    private String password;
    private List<UserRoleEntity> roles;
    private UserEntity userEntity;
}
