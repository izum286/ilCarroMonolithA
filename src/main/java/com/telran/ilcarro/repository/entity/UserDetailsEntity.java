package com.telran.ilcarro.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "userdetails")
public class UserDetailsEntity {
    @Id
    private String email;
    private String password;
    private List<UserRoleEntity> roles;
}
