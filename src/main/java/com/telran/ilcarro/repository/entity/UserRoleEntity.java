package com.telran.ilcarro.repository.entity;
import lombok.*;

import java.util.List;

/**
 * @author vitalii_adler
 * Use for auth
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserRoleEntity {
    private String role;
    private List<UserDetailsEntity> users_details;
}
