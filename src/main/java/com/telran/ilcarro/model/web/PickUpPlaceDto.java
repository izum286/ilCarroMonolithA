package com.telran.ilcarro.model.web;

import lombok.*;

/**
 * @author vitalii_adler
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PickUpPlaceDto {
    private String place_id;
    private float latitude;
    private float longitude;
}
