package com.telran.ilcarro.repository.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PickUpPlaceEntity {
    private String place_id;
    private float latitude;
    private float longitude;
}
