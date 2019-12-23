package com.telran.ilcarro.model.web.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class UpdUserDTO extends RegUserDTO {
    private String photo;
}
